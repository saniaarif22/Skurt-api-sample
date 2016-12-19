package service;

import java.util.LinkedList;
import java.util.Queue;

import model.Vehicle;

public class PollingService {
	/* Vehicles with Ids 1- 10 */
	private static final int NO_OF_VEHICLES = 10;
	/* Hit the Skurt end point for location every 150 seconds */
	private static final int SLEEP_TIME_MILLISECONDS = 150000;
	NotifyEmailsClient notifier = new NotifyEmailsClient();
	public Queue<Vehicle> vehicleQueue = new LinkedList<Vehicle>();
	
	public PollingService(Queue<Vehicle> vehicleQueue) {
		this.vehicleQueue = vehicleQueue;
	}

	/* Vehicles are polled for location,
	 * and notified if out of range, and then added
	 * back to the queue */
	public void monitor() {
		int count = 0;
		while (true) {
			count++;
			System.out.println("Queue Size in PollingService " + vehicleQueue.size());
			Vehicle curr = vehicleQueue.poll();
			System.out.println("Current from queue " + curr.getVehicleId());
			if(!curr.isInRange()) {
				if(notifier.notify(curr))
					System.out.println("E-mail alert sent!");
				else
					System.out.println("E-mail alert not sent.");
			}
			vehicleQueue.add(curr);
			/* finish iterating over all 10 vehicles */
			if (count == NO_OF_VEHICLES) {
				try {
					Thread.sleep(SLEEP_TIME_MILLISECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} /* if i == 10 */
		} /* while */
	} /* monitor() */
}
