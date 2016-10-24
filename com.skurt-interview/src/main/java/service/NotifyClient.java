package service;

import model.Vehicle;

/* Notify CLient, for all forms of 
 * notification clients to implement */

public interface NotifyClient {
	boolean notify(Vehicle vcehicle);
}
