package ProductCatalogueCom;

//Import packages
import ProductCatalogueModel.Product;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Products")
public class ProductService {
	
	//Create a Product Object in class level
	
	Product product = new Product();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProduct(
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
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProducts()
	 {
		return product.readProducts();
	 }
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProduct(String itemData)
	{
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	
		String pr_id = itemObject.get("pr_id").getAsString();
		String pr_code = itemObject.get("pr_code").getAsString();
		String pr_name = itemObject.get("pr_name").getAsString();
		String pr_category = itemObject.get("pr_category").getAsString();
		String pr_seller_id = itemObject.get("pr_seller_id").getAsString();
		String pr_origin_country = itemObject.get("pr_origin_country").getAsString();
		String pr_description = itemObject.get("pr_description").getAsString();
		String pr_price = itemObject.get("pr_price").getAsString();
		
		String status = product.updateProduct(pr_id, pr_code, pr_name, pr_category, pr_seller_id, pr_origin_country, pr_description, pr_price);
		
		return status;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProduct(String productDetails)
	{
	
		Document doc = Jsoup.parse(productDetails, "", Parser.xmlParser());

		String pr_id = doc.select("pr_id").text();
		String output = product.deleteProduct(pr_id);
		
		return output;
		
	}

}
