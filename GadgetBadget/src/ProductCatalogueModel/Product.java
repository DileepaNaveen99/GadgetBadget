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
	 
	 operationStatus = "Product Inserted Successfully";
	 
	 }
	 catch (Exception e)
	 {
		 
		 operationStatus = "Something went wrong! Plese Check the Details...";
		 System.err.println(e.getMessage());
	 }
	 
	 	return operationStatus;
	 	
	 }

}
