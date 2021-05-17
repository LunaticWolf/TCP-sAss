import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ComputeServer 
{

	public static void main(String[] args) 
	{
		try
		{
			int serverPort = 8888;
			ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Compute Server running...");

			while(true) 
			{
				Socket clientSocket = listenSocket.accept();
				// creates a new thread to deal with each client(Thread-per-connection)
				Connection c = new Connection(clientSocket);
			}

		}
		catch(IOException e) 
		{
			System.out.println("Listen socket:"+e.getMessage());
		}

	}

}
