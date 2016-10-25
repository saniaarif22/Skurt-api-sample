package service;

import model.Vehicle;

/* NotifyClient for sending E-mail notifications */
public class NotifyEmailsClient implements NotifyClient {

	/* Instantiates the SMTPClient to send an e-mail */
	public boolean notify(Vehicle vehicle) {
		SMTPClient smtp = new SMTPClient();
		return smtp.sendEmail(vehicle);
	}

}
