package BeneficiaryCom;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//import packages
import BeneficiaryModel.*;


@Path("/BeneficiaryDetails")

public class BeneficiaryService {
	
	
		 Beneficiary beneficiaryObj = new Beneficiary();
		 
		 @GET
		 @Path("/")
		 @Produces(MediaType.TEXT_HTML)
		 
		 public String readBeneficiaries()
		  {
		  return beneficiaryObj.readBeneficiaries();
		  }
		 
		 		 
		 @POST
		 @Path("/")
		 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		 @Produces(MediaType.TEXT_PLAIN)
		 
		 public String insertBeneficiary(@FormParam("InventorUser") String InventorUser,
		  @FormParam("InventorAge") int InventorAge,
		  @FormParam("InventorAddress") String InventorAddress,
		  @FormParam("InventorPassword") String InventorPassword)
		 {
		  String output = beneficiaryObj.insertBeneficiary(InventorUser, InventorAge, InventorAddress, InventorPassword);
		 return output;
		 }
		 
		 
		 
		 @PUT
		 @Path("/")
		 @Consumes(MediaType.APPLICATION_JSON)
		 @Produces(MediaType.TEXT_PLAIN)
		 
		 public String updateBeneficiary(String BeneficiaryDetails)
		 {
		 //Convert the input string to a JSON object
		  JsonObject Beneficiaryobj = new JsonParser().parse(BeneficiaryDetails).getAsJsonObject();
		  
		 //Read the values from the JSON object
		  String InventorID = Beneficiaryobj.get("InventorID").getAsString();
		  String InventorUser = Beneficiaryobj.get("InventorUser").getAsString();
		  String InventorAge = Beneficiaryobj.get("InventorAge").getAsString();
		  String InventorAddress = Beneficiaryobj.get("InventorAddress").getAsString();
		  String InventorPassword = Beneficiaryobj.get("InventorPassword").getAsString();
		  
		  String output = beneficiaryObj.updateBeneficiaries(InventorID, InventorUser, InventorAge, InventorAddress, InventorPassword);
		 return output;
		 
		 }
		 
		 
		 
		 
		 @DELETE
		 @Path("/")
		 @Consumes(MediaType.APPLICATION_XML)
		 @Produces(MediaType.TEXT_PLAIN)
		 
		 public String deleteBeneficiary(String BeneficiaryDetails)
		 {
			 
		 //Convert the input string to an XML document
		  Document doc = Jsoup.parse(BeneficiaryDetails, "", Parser.xmlParser());

		 //Read the value from the element <itemID>
		  String InventorID = doc.select("InventorID").text();
		  
		  String output = beneficiaryObj.deleteBeneficiaries(InventorID);
		 return output;
		 
		 }
		 


}