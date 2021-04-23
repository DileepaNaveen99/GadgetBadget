package CustomerOrderModel;

import java.sql.*;

public class CustomerOrder {

	private Connection connect()
	 {
	 Connection connect = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerorderdb", "root", "12345");
		
	 }
	 catch (Exception e)
	 {
		 e.printStackTrace();
	 }
	 
	 return connect;
	 }
	
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	public String addCustomer(String name, int age, String phone, String address, String email, String pass)
	 {
			
		String output = "";
	 
		try
		 {
					Connection connect = connect();
					
					if (connect == null)
					{
						return "Error while connecting to the database for inserting."; 
					}
					
					//prepared statement
					String query = " insert into customer (`customerId`,`customerName`,`customerAge`,`customerPhone`,`customerAddress`,`customerEmail`,`customerPassword)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
		 
					PreparedStatement preStatement = connect.prepareStatement(query);
		 
					preStatement.setInt(1, 0);
					preStatement.setString(2, name);
					preStatement.setInt(3, age);
					preStatement.setString(4, phone);
					preStatement.setString(5, address);
					preStatement.setString(6, email);
					preStatement.setString(7, pass);

					preStatement.execute();
					connect.close();
					output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while inserting the customer.";
			 System.err.println(e.getMessage());
		 }
		 	return output;
		 }
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public String readCustomers()
	 {
		
		String output = "";
		
	 try
	 {
		 Connection connect = connect();
		 	
		 if (connect == null)
		 {return "Error while connecting to the database for reading."; }
	 
		 output = "<table border='1'><tr><th>Customer ID</th><th>Customer Name</th>" +
				 "<th>Customer Age</th>" +
				 "<th>Customer Phone</th>" +
				 "<th>Customer Address</th>" +
				 "<th>Customer Email</th>" +
				 "<th>Customer Password</th>" +
				 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from customer";
	 Statement stmt = connect.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 	while (rs.next())
	 	{
		 	String customerId = Integer.toString(rs.getInt("customerId"));
		 	String customerName = rs.getString("customerName");
		 	String customerAge = Integer.toString(rs.getInt("customerAge"));
		 	String customerPhone = rs.getString("customerPhone");
		 	String customerAddress = rs.getString("customerAddress");
		 	String customerEmail = rs.getString("customerEmail");
		 	String customerPassword = rs.getString("customerPassword");
	 
		 	// Add into table
		 	output += "<tr><td>" + customerId + "</td>";
		 	output += "<td>" + customerName + "</td>";
		 	output += "<td>" + customerAge + "</td>";
		 	output += "<td>" + customerPhone + "</td>";
		 	output += "<td>" + customerAddress + "</td>";
		 	output += "<td>" + customerEmail + "</td>";
		 	output += "<td>" + customerPassword + "</td>";
	 
		 	// buttons
		 	output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 			 + "<td><form method='post' action='customer.jsp'>"
		 			 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 			 + "<input name='customerId' type='hidden' value='" + customerId
		 			 + "'>" + "</form></td></tr>";
	 	}
	 
	 	connect.close();
	 
	 	// Complete the table
	 	output += "</table>";
	 }
	 catch (Exception e)
	 {
		 	output = "Error while reading the customers.";
		 	System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	public String updateCustomer(String Id, String name, String age, String phone, String address, String email, String pass)
	{
		 String output = "";
		 
		 try
		 {
		 		Connection connect = connect();
		 		
		 		if (connect == null)
		 		{return "Error while connecting to the database for updating."; }
		 
		 		// create a prepared statement
		 		String query = " insert into customer (`customerId`,`customerName`,`customerAge`,`customerPhone`,`customerAddress`, `customerEmail`, `customerPassword`)"
		 				 + " values (?, ?, ?, ?, ?, ?, ?)";
		 
		 		PreparedStatement perStatement = connect.prepareStatement(query);

		 		perStatement.setString(1, name);
		 		perStatement.setInt(2, Integer.parseInt(age));
		 		perStatement.setString(3, phone);
		 		perStatement.setString(4, address);
		 		perStatement.setString(5, email);
		 		perStatement.setString(6, pass);
		 		perStatement.setInt(7, Integer.parseInt(Id));
		 		
		 		// execute the statement
		 		perStatement.execute();
		 		connect.close();
		 	
		 		output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 	output = "Error while updating the customer.";
			 	System.err.println(e.getMessage());
		 }
		 
		 return output;
		 
		 }
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	public String deleteCustomer(String customerId)
	 {
	 
			String output = "";
	 
			try
			{
				Connection connect = connect();
				
				if (connect == null)
				{return "Error while connecting to the database for deleting."; }
	 
				//prepared statement
				String query = "delete from customer where customerId=?";
				PreparedStatement preStatement = connect.prepareStatement(query);

				preStatement.setInt(1, Integer.parseInt(customerId));

				preStatement.execute();
				connect.close();
	 
				output = "Deleted successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting the customer.";
				System.err.println(e.getMessage());
			}
			return output;
	 }
	 
}