package LoginCom;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import BeneficiaryModel.Beneficiary;
import LoginModel.Login;


@Path("/Login")
public class LoginService {
	
	
	 Login LoginObj = new Login();
	 
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String validateLogin(@FormParam("InventorUser") String InventorUser, 
								    @FormParam("InventorPassword") String InventorPassword) 
		{
			String output = LoginObj.validateLogin(InventorUser, InventorPassword);
			return output;
		}
		
		

}
