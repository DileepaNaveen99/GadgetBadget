package PaymentModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class Payment {
	
	//This Method provides a proper Database Connection
	private Connection connect()
	{
		Connection mySQLconnection = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			mySQLconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/paymentDb", "root", "123");
			
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
		
		 	return mySQLconnection;
		 	
		 }
	
	//This Method allows to Insert new Products to the Catalogue
	
	public String insertPayment( String orderId, String amount, String userId)
	{
		String operationStatus = "";
		
	 try
	 {
		 Connection mySQLconnection = connect();
		 
		 if (mySQLconnection == null)
		 {
			 
			 return "Database Connection Failed. Insert Operation Failed!";
			 
		 }
		 
		 
	 
	 String sql_statement = " insert into payment (`paymentId`,`orderId`,`userId`,`amount`,`paymentDate`)"
	 + " values (?, ?, ?, ?, ?)";
	 
	 PreparedStatement preparedStmt = mySQLconnection.prepareStatement(sql_statement);
	 String paymentDate = LocalDate.now().toString();
	 
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, orderId);
	 preparedStmt.setString(3, userId);
	 preparedStmt.setDouble(4, Double.parseDouble(amount));
	 preparedStmt.setString(5, paymentDate);
	 
	 preparedStmt.execute();
	 mySQLconnection.close();
	 
	 operationStatus = "Paymente Inserted Successfully";
	 
	 }
	 catch (Exception e)
	 {
		 
		 operationStatus = "Something went wrong! Plese Check the Details...";
		 System.err.println(e.getMessage());
	 }
	 
	 	return operationStatus;
	 	
	 }
	
	public String readItems()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Payment Id Code</th><th>User Id</th>" +
	 "<th>Order Id</th>" +
	 "<th>Amount</th>" + "<th>Payment Date</th>" + 
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from payment";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String paymentId = Integer.toString(rs.getInt("paymentId"));
	 String orderId = rs.getString("orderId");
	 String userId = rs.getString("userId");
	 String amount = Double.toString(rs.getDouble("amount"));
	 String paymentDate = rs.getString("paymentDate");
	 
	 
	 // Add into the html table
	 output += "<tr><td>" + paymentId + "</td>";
	 output += "<td>" + userId + "</td>";
	 output += "<td>" + orderId + "</td>";
	 output += "<td>" + amount + "</td>";
	 output += "<td>" + paymentDate + "</td>";
	 // buttons
	 
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + paymentId
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the payments.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

}
