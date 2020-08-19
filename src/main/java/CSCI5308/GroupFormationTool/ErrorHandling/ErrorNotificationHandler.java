package CSCI5308.GroupFormationTool.ErrorHandling;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public abstract class ErrorNotificationHandler {

	private String from = System.getenv("email");
	private String pass = System.getenv("emailPassword");
	private String subject = System.getenv("subject");

	private Properties properties = null;
	private Session session = null;
	protected Message msg = null;

	private void setProperties() throws AddressException, MessagingException {
		properties = new Properties();
		properties.put("mail.smtp.auth", System.getenv("auth"));
		properties.put("mail.smtp.starttls.enable", System.getenv("tls"));
		properties.put("mail.smtp.host", System.getenv("host"));
		properties.put("mail.smtp.port", System.getenv("port"));

		session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pass);
			}
		});

		msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from, false));
		msg.setSubject(subject);
	}

	private void setEmail(String email) throws AddressException, MessagingException {
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
	}

	protected abstract void setMessage() throws MessagingException;

	public void sendNotification(String email) {
		try {
			setProperties();
			setEmail(email);
			setMessage();
			Transport.send(msg);
		} catch (AddressException e) {
			System.out.println("Invalid email address!");
		} catch (MessagingException e) {
			System.out.println("Invalid message format!");
		}
	}
}