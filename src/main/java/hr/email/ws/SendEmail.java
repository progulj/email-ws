package hr.email.ws;

import hr.email.ws.model.Email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	
     void sendEmail(Email email) throws Exception {

    	// Recipient's email ID needs to be mentioned.
    	final String to = email.getEmailTo();

    	// Sender's email ID needs to be mentioned
    	final String from = email.getEmailFrom();

    	// Sender's email ID needs to be mentioned
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
    	    Message message = new MimeMessage(session);
    	    message.setFrom(new InternetAddress(from));
    	    message.setRecipients(Message.RecipientType.TO,
    		    InternetAddress.parse(to));
    	    message.setSubject(email.getSubject());
    	    message.setText(email.getMessage());
    	    Transport.send(message);
    	} catch (MessagingException messageException) {
    	    throw new RuntimeException(messageException);
    	}
        }

}
