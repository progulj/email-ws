package hr.email.ws;

import java.util.Properties;

import hr.email.ws.model.Email;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




@Path("/mail")
public class EmailRestController {
	



	 

	    @POST
	    @Path("/send")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)

	    public Response consumeJSON( Email mail ) {

	    	SendEmail sMail = new SendEmail();
	    	try {
	    		sMail.sendEmail(mail);
				return Response.status(200).entity("Email sended!").build();
			} catch (Exception e) {
				
				e.printStackTrace();
				return Response.status(422).entity("Email not sended!").build();
			}

	        
	
	    

	}
	    
	


}
