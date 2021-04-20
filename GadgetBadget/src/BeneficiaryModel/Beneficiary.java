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
	
	
	
}
