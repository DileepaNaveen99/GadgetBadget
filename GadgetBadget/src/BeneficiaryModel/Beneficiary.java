package BeneficiaryModel;

import java.sql.*;

public class Beneficiary {
	
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

	// insert method to beneficiary  part
	
		public String insertBeneficiary(String username, int age , String address, String password) {
			Connection con = connect();
			String output = "";
			if (con == null) {
				
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into beneficiaries (`InventorID`,`InventorUser`,`InventorAge`,`InventorAddress`,`InventorPassword`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt;
			try {
				preparedStmt = con.prepareStatement(query);

				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, username);
				preparedStmt.setInt(3, age);
				preparedStmt.setString(4, address);
				preparedStmt.setString(5, password);

				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
				
			} catch (SQLException e) {
				
				output = "Error while inserting";
				System.err.println(e.getMessage());
				
			}

			return output;
		}

	// retrieve method to 
	public String readBeneficiaries()
	 {
		String output = "";
	 try
	 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading."; 
		 }
		 // Prepare the html table to be displayed
		 
		 output = "<table border='1'><tr><th>Beneficiary username</th><th>Beneficiary age</th>" +
		 "<th>Beneficiary address</th>" +
		 "<th>Beneficiary password</th>" +
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from beneficiaries";
		 
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String InventorID = Integer.toString(rs.getInt("InventorID"));
		 String InventorUser = rs.getString("InventorUser");
		 String InventorAge =Integer.toString(rs.getInt("InventorAge"));
		 String InventorAddress =  rs.getString("InventorAddress");
		 String InventorPassword = rs.getString("InventorPassword");
	 
	 
		 // Add into the html table
		 output += "<tr><td>" + InventorUser + "</td>";
		 output += "<td>" + InventorAge + "</td>";
		 output += "<td>" + InventorAddress + "</td>";
		 output += "<td>" + InventorPassword + "</td>";
	 
		 // buttons
		 
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='items.jsp'>"
		 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='InventorID' type='hidden' value='" + InventorID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
	 
		 
		 // Complete the html table
		 
		 output += "</table>";
		 
		 }
	 
		 catch (Exception e)
	 
		 {
			 
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 
		 }
	 
		 return output;
		 
		 } 
	
	
	
		//update beneficiary details...........................
		
		public String updateBeneficiaries(String id,String username, String age , String address, String password)
		
		 {
				 String output = "";
				 
				 try
				 {
					 Connection con = connect();
				 if (con == null)
					 {
						 return "Error while connecting to the database for updating."; 
					 }
				 
				 
				 // create a prepared statement
				 
				 
				 String query = " update beneficiaries set InventorUser= ? , InventorAge = ? , InventorAddress = ? , InventorPassword = ?  where InventorID = ? ";
					
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 preparedStmt.setString(1, username);
				 preparedStmt.setInt(2, Integer.parseInt(age));
				 preparedStmt.setString(3, address);
				 preparedStmt.setString(4, password);
				 preparedStmt.setInt(5, Integer.parseInt(id));
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Updated successfully";
				 
				 }
				 catch (Exception e)
				 {
					 
					 output = "Error while updating the beneficiary detail.";
					 System.err.println(e.getMessage());
				 }
				 return output;
		 }
		
		
	
		// method to deleting beneficiary details
		
		public String deleteBeneficiaries(String InventorID)
		 {
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for deleting."; 
				 }
				 
				 // create a prepared statement
					 
				 String query = "delete from beneficiaries where InventorID=?";
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(InventorID));
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Deleted successfully";
				 
			 }
			 catch (Exception e)
			 {
				 
			 output = "Error while deleting the Beneficiary Details.";
			 System.err.println(e.getMessage());
			 
			 }
			 
			 return output;
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
