package BeneficiaryCom;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

}