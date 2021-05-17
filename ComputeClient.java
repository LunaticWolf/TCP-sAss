import java.net.*;
import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class ComputeClient 
{
	public static void main (String args[]) 
	{

		Socket socket = null;
		try
		{
			int serverPort = 8888;
			socket = new Socket("localhost", serverPort);
		
			ObjectInputStream in = null;
			ObjectOutputStream out =null;

			out =new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

			Scanner scanner = new Scanner(System.in);
			while(true)
			{
				
              String userPass = userMenu(scanner);
              out.writeObject(userPass);
              userPass = (String)in.readObject();
           
              if(!(userPass.equals("INVALID USER") || userPass.equals("INVALID PASSWORD")))
              {
            	  System.out.println("********SUCCESS*******" );
            	  while(true)
            	  {
		              Task task = computeMenu(scanner, userPass);
		              out.writeObject(task);
		              task = (Task)in.readObject();
		              System.out.println(task.getResults());
            	  }
              }
              else
            	  System.out.println("*********" + userPass + "**********");

			}
		}
		catch(UnknownHostException e)
		{
					System.out.println("Socket:"+e.getMessage());
		}
		catch(EOFException e)
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
			if(socket!=null) 
				try 
				{
					socket.close();
				}
				catch(IOException e)
				{
					System.out.println("close:"+e.getMessage());
				}
		}
	}     


	public static String userMenu(Scanner scanner)
	{
		System.out.print("Enter User Name:\n");
		String user = scanner.nextLine();
		System.out.print("Enter User Password:\n");
		String password = scanner.nextLine();
		
		String auth = user + ":" + password;	
		return auth;
	}


	public static Task computeMenu(Scanner scanner,String user)
	{
		System.out.println("PLEASE MAKE YOUR SELECTION " + user);
		System.out.println("**************************");
		System.out.println("1.Fibonacci \n2.Factorial \n3.GCD \n4.Exit");
		System.out.println("**************************");
		System.out.println("Enter your option:");
		int choice=scanner.nextInt();
		
		Task compute = null;
		
		if(choice==1)
		{
			System.out.println("Enter your range limit");
			compute = new Fibonacci(scanner.nextInt());
		}
		else if(choice==2)
		{
			System.out.println("Enter your range limit");
			compute = new Factorial(scanner.nextInt());
		}
		else if(choice==3)
		{
			System.out.println("Enter Number 1:");
			int num1 = scanner.nextInt();
			System.out.println("Enter Number 2:");
			int num2 = scanner.nextInt();
			
			compute = new GCD(num1, num2);
		}
		else if(choice==4)
		{
			System.exit(0);
		}
		else
		{
			System.out.println("******INVALID SELECTION******");
			computeMenu(scanner, user);
		}
		
		return compute;
		
	}
}