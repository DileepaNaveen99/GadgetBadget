package PaymentService;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import PaymentModel.Payment;


@Path("/payment")
public class PaymentService {
	
	Payment payment = new Payment();
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(
			@FormParam("userId") String userId,
			@FormParam("orderId") String orderId,
			@FormParam("amount") String amount
			)
		
		
	{
		
		String status = payment.insertPayment(orderId, amount, userId );
		return status;
		
	}
	
	@GET
	 @Path("/readAsHtml")
	 @Produces(MediaType.TEXT_HTML)
	 public String getAllPaymentsAsHtml()
	  {
		return payment.readItems();
	  }

}