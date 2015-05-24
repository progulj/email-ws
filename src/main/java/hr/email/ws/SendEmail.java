package hr.email.ws;

import hr.email.ws.model.Email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.mail.smtp.SMTPTransport;

public class SendEmail {
	
	private  static String  webRes = "https://api.mailgun.net/v3/sandboxe32639b06e5c49a3987c4a2cafaf9ea4.mailgun.org/messages";
	private static String  filter = "key-a20da2ff2a8adfe80ac3e67136252ce0";
	
	
	void sendEmail(Email email) throws Exception {

		final String to = email.getEmailTo();

		final String name = email.getName();

		final String lastName = email.getLastName();

		final String from = email.getEmailFrom();

		final String password = email.getPassword();

		// Assuming you are sending email from smtp.gmail.com
		String host = "smtp.gmail.com";

		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(to, password);
			}

		});

		session.setDebug(true);

		try {

			InternetAddress fromAddress = new InternetAddress(from, name + " "
					+ lastName);

			Message message = new MimeMessage(session);
			message.setFrom(fromAddress);
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(email.getSubject());
			message.setText(email.getMessage() + " " + from);
			Transport.send(message);
		} catch (MessagingException messageException) {
			throw new RuntimeException(messageException);
		}catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	void sendEmailMailGun(Email email) throws MessagingException {

		Properties props = System.getProperties();
		props.put("mail.smtps.host", "smtp.mailgun.org");
		props.put("mail.smtps.auth", "true");
		Session session = Session.getInstance(props, null);

		session.setDebug(true);

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(email.getEmailFrom()));
		msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email.getEmailTo(), false));
		msg.setSubject(email.getSubject());
		msg.setText(email.getMessage());
		msg.setSentDate(new Date());

		SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
		t.connect("smtp.mailgun.com", email.getEmailTo(), email.getPassword());
		t.sendMessage(msg, msg.getAllRecipients());
		System.out.println("Response: " + t.getLastServerResponse());
		t.close();

	}
	
	public static ClientResponse SendSimpleMessage(Email email) {
	    Client client = Client.create();
	    client.addFilter(new HTTPBasicAuthFilter("api",filter));
	    WebResource webResource =
	        client.resource(webRes);
	    MultivaluedMapImpl formData = new MultivaluedMapImpl();
	    formData.add("from", email.getName()+" <"+email.getEmailFrom()+">");
	    formData.add("to", email.getEmailTo());
	    formData.add("subject", email.getSubject());
	    formData.add("text", email.getMessage());
	    return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
	                                                post(ClientResponse.class, formData);
	}

}
