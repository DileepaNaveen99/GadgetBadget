package LoginModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
	
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/beneficarydb","root", "chalana1@");
			 
			// For testing
			System.out.print("Successfully connected");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return con;
	}

	
	public String validateLogin(String userName, String password) 
	{
		try
		{
			Connection con = connect();
				
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}
				
			String query1 = "select InventorUser, InventorPassword from beneficiaries";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query1);
				
			while(rs.next())
			{
				String un = rs.getString("InventorUser");
				String pass = rs.getString("InventorPassword");
					
				if(userName.equals(un) && password.equals(pass))
				{
					return "Welcome "+ userName;
				}
				else if(userName.equals("admin") && password.equals("admin"))
				{
					return "Welcome Admin";
				}
			}
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		return "Username or password incorrect";
	}
	

}
