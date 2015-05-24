package hr.email.ws;

import hr.email.ws.model.Email;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.ClientResponse;

@Path("/mail")
public class EmailRestController {

	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response consumeJSON(Email mail) {

		
		try {
			ClientResponse response = SendEmail.SendSimpleMessage( mail);
			
			return Response.status(response.getStatus()).entity(response.getEntityTag()).build();
			
		} catch (Exception e) {

			e.printStackTrace();
			return Response.status(452).entity("Email not sended!").build();
		}

	}

}
