package ProductCatalogueModel;

//import entire SQL package to perform database operations
import java.sql.*;

public class Product {
	
	//This Method provides a proper Database Connection
	private Connection connect()
	{
		Connection mySQLconnection = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			mySQLconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cataloguedb", "root", "12345");
			
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
		
		 	return mySQLconnection;
		 	
		 }
	
	//This Method allows to Insert new Products to the Catalogue
	//First step in every method is to call connect method to connect to the database
	
	public String insertProduct(String pr_code, String pr_name, String pr_category, int pr_seller_id, String pr_origin_country, String pr_description, String pr_price)
	{
		String operationStatus = "";
		
	 try
	 {
		 Connection mySQLconnection = connect();
		 
		 if (mySQLconnection == null)
		 {
			 
			 return "Database Connection Failed. Insert Operation Failed!";
			 
		 }
		 
		 
	 //insert query
	 String sql_statement = " insert into product (`productID`,`productCode`,`productName`,`productCategory`,`productSellerID`, `productOriginCountry`, `productDescription`, `productPrice`)"
	 + " values (?, ?, ?, ?, ?, ?, ?, ?)";
	 
	 PreparedStatement preparedStmt = mySQLconnection.prepareStatement(sql_statement);
	 
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, pr_code);
	 preparedStmt.setString(3, pr_name);
	 preparedStmt.setString(4, pr_category);
	 preparedStmt.setInt(5, pr_seller_id);
	 preparedStmt.setString(6, pr_origin_country);
	 preparedStmt.setString(7, pr_description);
	 preparedStmt.setDouble(8, Double.parseDouble(pr_price));
	 
	 preparedStmt.execute();
	 mySQLconnection.close();
	 
	 operationStatus = "Product Inserted Successfully...";
	 
	 }
	 catch (Exception e)
	 {
		 
		 operationStatus = "Something went wrong! Please Check the Details...";
		 System.err.println(e.getMessage());
	 }
	 
	 	return operationStatus;
	 	
	 }
	
	
	//This Method allows to Read Product details from the cataloguedb
	public String readProducts()
	{
		
		String operationStatus = "";
		
		try
		{
			Connection mySQLconnection = connect();
			
			if (mySQLconnection == null)
			{
				
				return "Database Connection Failed. Read Operation Failed!";
				
			}
			
	operationStatus = "<table border='1'><tr><th>Product Code</th><th>Product Name</th>" +
	 "<th>Product Category</th>" +
	 "<th>Product Product Seller ID</th>" +
	 "<th>Product Origin Country</th>" +
	 "<th>Product Description</th>" +
	 "<th>Product Price</th>" +
	 "<th>Update</th><th>Delete</th></tr>";

	 //read sql query
	 String sql_statement = "select * from product";
	 
	 Statement statement = mySQLconnection.createStatement();
	 
	 ResultSet rs = statement.executeQuery(sql_statement);
	 
	 
	 //loop the result set and create the table
	 while (rs.next())
	 {
		 
		 String pr_id = Integer.toString(rs.getInt("productID"));
		 String pr_code = rs.getString("productCode");
		 String pr_name = rs.getString("productName");
		 String pr_category = rs.getString("productCategory");
		 String pr_seller_id = Integer.toString(rs.getInt("productSellerID"));
		 String pr_origin_country = rs.getString("productOriginCountry");
		 String pr_description = rs.getString("productDescription");
		 String pr_price = Double.toString(rs.getDouble("productPrice"));
		 
		 
		 operationStatus += "<tr><td>" + pr_code + "</td>";
		 operationStatus += "<td>" + pr_name + "</td>";
		 operationStatus += "<td>" + pr_category + "</td>";
		 operationStatus += "<td>" + pr_seller_id + "</td>";
		 operationStatus += "<td>" + pr_origin_country + "</td>";
		 operationStatus += "<td>" + pr_description + "</td>";
		 operationStatus += "<td>" + pr_price + "</td>";
		 
	 
		 operationStatus += "<td><input name='Update_BTN' type='button' value='Update' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='product.jsp'>"
				 + "<input name='Delete_BTN' type='submit' value='Delete' class='btn btn-danger'>"
				 + "<input name='itemID' type='hidden' value='" + pr_id
				 + "'>" + "</form></td></tr>";
	 }
	 
	 mySQLconnection.close();
	 
	 operationStatus += "</table>";
	 
	 }
	 catch (Exception e)
	 {
		 
		 operationStatus = "Something went wrong! Product Details not available...";
		 System.err.println(e.getMessage());
		 
	 }
		
	 return operationStatus;
	 
	 }
	
	
	//This Method allows to Update existing Products in the Catalogue
	public String updateProduct(String pr_id, String pr_code, String pr_name, String pr_category, String pr_seller_id, String pr_origin_country, String pr_description, String pr_price)
	{
		 String operationStatus = "";
		 
		 try
		 {
			 Connection mySQLconnection = connect();
			 
			 if (mySQLconnection == null)
			 {
				 return "Database Connection Failed. Update Operation Failed!";
			 }
			 
		
			 //update sql query
			 String sql_statement = "UPDATE product SET productCode=?, productName=?, productCategory=?, productSellerID=?, productOriginCountry=?, productDescription=?, productPrice=? WHERE productID=?";
			 
			 PreparedStatement preparedStmt = mySQLconnection.prepareStatement(sql_statement);
		 
			 preparedStmt.setString(1, pr_code);
			 preparedStmt.setString(2, pr_name);
			 preparedStmt.setString(3, pr_category);
			 preparedStmt.setDouble(4, Integer.parseInt(pr_seller_id));
			 preparedStmt.setString(5, pr_origin_country);
			 preparedStmt.setString(6, pr_description);
			 preparedStmt.setDouble(7, Double.parseDouble(pr_price));
			 preparedStmt.setInt(8, Integer.parseInt(pr_id));
		 
		 
			 preparedStmt.execute();
		 
			 mySQLconnection.close();
		 
			 operationStatus = "Product Updated Successfully...";
		 
		 }
		 catch (Exception e)
		 {
			 	operationStatus = "Something went wrong! Please Check the Details...";
			 	System.err.println(e.getMessage());
		 }
		 
		 	return operationStatus;
		 	
		 }
	
	
	//This Method allows to Delete existing Products from the Catalogue
	public String deleteProduct(String pr_id)
	{
		
		String operationStatus = "";
		
		try
		{
			Connection mySQLconnection = connect();
			
			if (mySQLconnection == null)
			{
				return "Database Connection Failed. Delete Operation Failed!";
			}
			
	
			//delete sql query
			String sql_statement = "delete from product where productID=?";
	 
			PreparedStatement preparedStmt = mySQLconnection.prepareStatement(sql_statement);
	 
	 
			preparedStmt.setInt(1, Integer.parseInt(pr_id));
			preparedStmt.execute();
	 
			mySQLconnection.close();
	 
			operationStatus = "Product Deleted Successfully...";
	 
		}
		catch (Exception e)
		{
			operationStatus = "Something went wrong! Please Check the Product ID...";
			System.err.println(e.getMessage());
		}
		
			return operationStatus;
		
	 }

}
