package CustomerOrderCom;

import CustomerOrderModel.CustomerOrder;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/CustomerOrder")
public class CustomerOrederService {
	
	CustomerOrder cust = new CustomerOrder();
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(@FormParam("customerName") String customerName,
			@FormParam("customerAge") int customerAge,
			@FormParam("customerPhone") String customerPhone,
			@FormParam("customerAddress") String customerAddress,
			@FormParam("customerEmail") String customerEmail,
			@FormParam("customerPass") String customerPass)
	{
			String output = cust.addCustomer(customerName, customerAge, customerPhone, customerAddress, customerEmail, customerPass);
	
			return output;
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomers()
	 {
			return cust.readCustomers();
	 }
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customerInfo)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(customerInfo).getAsJsonObject();
		
		//Read the values from the JSON object
		String customerId = itemObject.get("customerId").getAsString();
		String customerName = itemObject.get("customerName").getAsString();
		String customerAge = itemObject.get("customerAge").getAsString();
		String customerPhone = itemObject.get("customerPhone").getAsString();
		String customerAddress = itemObject.get("customerAddress").getAsString();
		String customerEmail = itemObject.get("customerEmail").getAsString();
		String customerPassword = itemObject.get("customerPassword").getAsString();
		String output = cust.updateCustomer(customerId, customerName, customerAge, customerPhone, customerAddress, customerEmail, customerPassword);
		return output;
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String customerInfo)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(customerInfo, "", Parser.xmlParser());

		//Read the value from the element <customerID>
		String customerId = doc.select("customerId").text();
		String output = cust.deleteCustomer(customerId);
		return output;
	}
	
}
