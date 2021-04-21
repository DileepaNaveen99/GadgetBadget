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
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
			return cust.readCustomers();
	 }

}
