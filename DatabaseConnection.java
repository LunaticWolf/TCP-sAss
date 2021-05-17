import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection
{
	
	static Connection connection = null;
	static PreparedStatement queryCheckUser = null;
	static PreparedStatement queryCheckPassword = null;
	static ResultSet rs = null;
	
	static String user = null;
	static String password = null;
	
	//path to database - users
	static String url = "jdbc:mysql://localhost:3306/users";
	 	
	//Get Database Connection
	public Connection DatabaseConnection(String url)
	{
		
		//Database user-name & password
		String dBUser = "root";
		String dBPassword = "root";
		
		//Driver Class - mysql
		String driver = "com.mysql.jdbc.Driver";
		
		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(url, dBUser, dBPassword);
		}
		catch(ClassNotFoundException | SQLException e)
		{
			System.out.println("Database Connection Failed - " + e.getMessage());
		}
		
		return connection;
	
		
	}

	//Check Login Credentials - User-name 
	public boolean checkUser(String user)
	{
		
		DatabaseConnection db = new DatabaseConnection();
		boolean userCheck = false;
		try
		{
			connection = db.DatabaseConnection(url);
			//Fetch User ID if valid
			queryCheckUser = connection.prepareStatement("Select * from users where userid=?");		
			queryCheckUser.setString(1, user);
			rs = queryCheckUser.executeQuery();
			while(rs.next())
			{
				userCheck = true;
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("User Not Found" + e.getMessage());
		}
		
		db.close();
		
		return userCheck;
		
	}
	
	//Check Login Credentials - Password w.r.t Username
	public boolean checkPassword(String user, String password)
	{
		
		DatabaseConnection db = new DatabaseConnection();
		boolean userPasswordCheck = false;
		try
		{
			connection = db.DatabaseConnection(url);
			//Fetch password w.r.t entered user id
			queryCheckPassword = connection.prepareStatement("Select userpassword from users where userid=?");		
			queryCheckPassword.setString(1, user);
			rs = queryCheckPassword.executeQuery();
			while(rs.next())
			{
				String retrievedPassword = rs.getString(1);
				if(retrievedPassword.equals(password))
				userPasswordCheck = true;
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("Password for UserID : "+ user + " Not Found" + e.getMessage());
		}
		
		
		db.close();
		
		return userPasswordCheck;
	}
	
	//Close all open connections
	public void close()
	{
		try
		{		
				if(connection!=null)
					connection.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	

}
