package ProductCatalogueCom;

//Import packages
import ProductCatalogueModel.Product;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Prodects")
public class ProductService {
	
	//Create a Product Object in class level
	
	Product product = new Product();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(
			@FormParam("pr_code") String pr_code,
			@FormParam("pr_name") String pr_name,
			@FormParam("pr_category") String pr_category,
			@FormParam("pr_seller_id") int pr_seller_id,
			@FormParam("pr_origin_country") String pr_origin_country,
			@FormParam("pr_description") String pr_description,
			@FormParam("pr_price") String pr_price)
	{
		
		String status = product.insertProduct(pr_code, pr_name, pr_category, pr_seller_id, pr_origin_country, pr_description, pr_price);
		return status;
		
	}

}
