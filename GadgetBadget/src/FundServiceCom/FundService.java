package FundServiceCom;

import FundModel.Fund;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Funds")
public class FundService {
	Fund fundObj = new Fund();


	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readFunds() 
	 { 
	 return fundObj.readFunds(); 
	 }

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertFund(@FormParam("funderName") String funderName, 
	 @FormParam("funderAddress") String funderAddress,
	 @FormParam("companyName") String companyName,
	 @FormParam("companyAddress") String companyAddress,
	 @FormParam("funderEmail") String funderEmail,
	 @FormParam("funderPhone") String funderPhone,
	 @FormParam("fundAmount") String fundAmount) 
	{ 
	 String output = fundObj.insertFund(funderName, funderAddress, companyName, companyAddress, funderEmail, funderPhone, fundAmount); 
	return output; 
	}

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateFund(String fundData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String fundID = fundObject.get("fundID").getAsString(); 
	 String funderName = fundObject.get("funderName").getAsString(); 
	 String funderAddress = fundObject.get("funderAddress").getAsString();
	 String companyName = fundObject.get("companyName").getAsString();
	 String companyAddress = fundObject.get("companyAddress").getAsString();
	 String funderEmail = fundObject.get("funderEmail").getAsString();
	 String funderPhone = fundObject.get("funderPhone").getAsString();
	 String fundAmount = fundObject.get("fundAmount").getAsString(); 
	 String output = fundObj.updateFund(fundID, funderName, funderAddress, companyName, companyAddress, funderEmail, funderPhone, fundAmount); 
	return output; 
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteFund(String fundData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <fundID>
	 String fundID = doc.select("fundID").text(); 
	 String output = fundObj.deleteFund(fundID); 
	return output; 
	}

	
}