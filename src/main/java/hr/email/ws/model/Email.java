package hr.email.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "email")
public class Email {

    private String name;
    private String lastName;
    private String emailTo;
    private String emailFrom;
    private String message;
    private String subject;
    private String password;
    
    public String getEmailTo() {
        return emailTo;
    }
    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }
    public String getEmailFrom() {
        return emailFrom;
    }
    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getPassword() {
	return password;
    }
    public void setPassword(String password) {
	this.password = password;
    }
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Email [emailTo=" + emailTo + ", emailFrom=" + emailFrom
				+ ", message=" + message + ", subject=" + subject
				+ ", password=" + password + "]";
	}
    
    

}
