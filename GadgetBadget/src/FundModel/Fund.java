package FundModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "root123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

public String insertFund(String fname, String faddress, String cname, String caddress, String email, String phone, String amount) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into funds (`fundID`,`funderName`,`funderAddress`,`companyName`,`companyAddress`,`funderEmail`,`funderPhone`,`fundAmount`)"
 + " values (?, ?, ?, ?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, fname); 
 preparedStmt.setString(3, faddress);
 preparedStmt.setString(4, cname);
 preparedStmt.setString(5, caddress);
 preparedStmt.setString(6, email);
 preparedStmt.setString(7, phone);
 preparedStmt.setDouble(8, Double.parseDouble(amount));  
// execute the statement3
 preparedStmt.execute(); 
 con.close(); 
 output = "Inserted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while inserting the fund."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 }

public String readFunds() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Funder Name</th><th>Funder Address</th>" +
 "<th>Company Name</th>" +
 "<th>Company Address</th>" +
 "<th>Funder Email</th>" +
 "<th>Funder Phone</th>" +
 "<th>Fund Amount</th>" +
 "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from funds"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String fundID = Integer.toString(rs.getInt("fundID")); 
 String funderName = rs.getString("funderName"); 
 String funderAddress = rs.getString("funderAddress");
 String companyName = rs.getString("companyName");
 String companyAddress = rs.getString("companyAddress");
 String funderEmail = rs.getString("funderEmail");
 String funderPhone = rs.getString("funderPhone");
 String fundAmount = Double.toString(rs.getDouble("fundAmount"));  
 // Add into the html table
 output += "<tr><td>" + funderName + "</td>"; 
 output += "<td>" + funderAddress + "</td>"; 
 output += "<td>" + companyName + "</td>";
 output += "<td>" + companyAddress + "</td>";
 output += "<td>" + funderEmail + "</td>";
 output += "<td>" + funderPhone + "</td>";
 output += "<td>" + fundAmount + "</td>"; 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
 + "<td><form method='post' action='funds.jsp'>" 
		 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='fundID' type='hidden' value='" + fundID 
 + "'>" + "</form></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the funds."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 }

public String updateFund(String ID, String fname, String faddress, String cname, String caddress, String email, String phone, String amount)
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for updating."; } 
 // create a prepared statement
 String query = "UPDATE funds SET funderName=?,funderAddress=?,companyName=?,companyAddress=?,funderEmail=?,funderPhone=?,fundAmount=? WHERE fundID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, fname); 
 preparedStmt.setString(2, faddress);
 preparedStmt.setString(3, cname);
 preparedStmt.setString(4, caddress);
 preparedStmt.setString(5, email);
 preparedStmt.setString(6, phone);
 preparedStmt.setDouble(7, Double.parseDouble(amount));  
 preparedStmt.setInt(8, Integer.parseInt(ID)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Updated successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while updating the fund."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 }

	public String deleteFund(String fundID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from funds where fundID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(fundID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the fund.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
