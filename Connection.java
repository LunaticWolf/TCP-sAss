import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class Connection extends Thread 
{

	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;
	DatabaseConnection db;

	public Connection (Socket aClientSocket) 
	{

		try 
		{
			clientSocket = aClientSocket;
			in = new ObjectInputStream( clientSocket.getInputStream());
			out =new ObjectOutputStream( clientSocket.getOutputStream());
			this.start();
		} 
		catch(IOException e) 
		{
			System.out.println("Connection:"+e.getMessage());
		}
	}

	public void run()
	{

		try 
		{
			while(true)
			{

					db = new DatabaseConnection();
					String auth="";
					boolean validAuth=false;
		         	
		         	String userCredentials = (String)in.readObject();
		         	String[] userData = userCredentials.split(":");
		         	if(db.checkUser(userData[0]) && db.checkPassword(userData[0], userData[1]))
		         	{
		         		System.out.println("User Authenticated");
		         		validAuth=true;
		         		auth=userData[0];
		         		out.writeObject(auth);
		         	}
		         	else if(!db.checkUser(userData[0]))
		         	{
		         		out.writeObject("INVALID USER");
		         	}
		         	else if(!db.checkPassword(userData[0], userData[1]))
		         	{
		         		out.writeObject("INVALID PASSWORD");
		         	}
		         		
				
	   
	             //keep computing the objects sent from the client
	             while(validAuth)
	             {
					  //read compute object from the stream	 
					  Task t = (Task)in.readObject();
					  //compute task for the given instance
					  t.executeTask();
					  //write the object back to the stream.
					  out.writeObject(t);
					  //Message displayed in Server w.r.t task type
				  	  System.out.println(t.getClass().toString().substring(6) + " task received and computed\n");
 			     }

			}



		}
		catch (EOFException e)
		{
			System.out.println("EOF:"+e.getMessage());
		}
		catch(IOException e) 
		{
			System.out.println("readline:"+e.getMessage());
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try 
			{
				clientSocket.close();}catch (IOException e){/*close failed*/
					
			}
		}


	}

}