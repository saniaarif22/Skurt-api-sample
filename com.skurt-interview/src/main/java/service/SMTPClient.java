package service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.Car;
import model.Vehicle;

public class SMTPClient {
	/* Populate fields as required */
	private static final String TO_ADDRESS = "";
	private static String FROM = "";
	private static String HOST = "smtp.gmail.com";
	private static String PASSWORD = "";

	/* Standard java mail code stub to send an e-mail */
	public boolean sendEmail(Vehicle vehicle) {
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", HOST);
		props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.user", FROM);
        props.put("mail.smtp.password", PASSWORD);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        
		Session session = Session.getDefaultInstance(props);
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM));
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(TO_ADDRESS));
			message.setSubject("Status update for Car#" + vehicle.getVehicleId());
			message.setContent("<h3>The following car is out of range <br>" + 
								vehicle.getVehicleId() +  "</h3>", "text/html");

			Transport transport = session.getTransport("smtp");
            transport.connect(HOST, FROM, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Send message Success!");
            /* E-mail alert sent */
            return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false; /* No e-mail alert sent */
			}
	}
 }
