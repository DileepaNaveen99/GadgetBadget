package DeliveryService;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DeliveryModel.Delivery;

@Path("/delivery")
public class DeliveryService {
	
	Delivery delivery = new Delivery();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(
			@FormParam("userId") String userId,
			@FormParam("orderId") String orderId,
			@FormParam("paymentId") String paymentId,
			@FormParam("address") String address,
			@FormParam("status") String status,
			@FormParam("deliveryPersionId") String deliveryPersionId
			)
		
		
	{
		String stat = delivery.insertDelivery(orderId, paymentId, userId, address, deliveryPersionId, status );
		return stat;
		
	}
	
	@GET
	 @Path("/readAsHtml")
	 @Produces(MediaType.TEXT_HTML)
	 public String getAllPaymentsAsHtml()
	  {
		return delivery.readItems();
	  }

}