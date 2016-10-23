import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

import model.Vehicle;
import service.ParserService;
import service.PollingService;
import service.SkurtAPIService;

public class SkurtPoller {
	public static void main(String args[]) {
		Queue<Vehicle> vehicleQueue = new LinkedList<Vehicle>();
		SkurtAPIService skurtAPIservice = new SkurtAPIService();
		ParserService parserService = new ParserService();
		
		/* Polling data from SkurtAPIService,
		 * Parsing the JSON using ParserService
		 * and adding to a queue */
		for(int i = 1; i<= 10; i++) {
			InputStream is = skurtAPIservice.sendRequest(i);
			if (is != null) {
				Vehicle vehicle = parserService.parse(i, is);
				if (vehicle != null)
					vehicleQueue.add(vehicle);
				}
			}
		System.out.println("Vehicle queue size in main: " + vehicleQueue.size());
		PollingService pollingService = new PollingService(vehicleQueue);
		System.out.println("Vehicle queue size of pollingService in main: " + pollingService.vehicleQueue.size());
		pollingService.monitor();
	}

}
