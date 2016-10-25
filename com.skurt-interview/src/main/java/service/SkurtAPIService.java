package service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SkurtAPIService {
	
	/* Sends a request to Skurt's API end point
	 * to get Coordinates and Polygon for Vehicle */
	public InputStream sendRequest(int vehicle_id) {
		InputStream is = null;
		try {
			System.out.println("GETTING from http://skurt-interview-api.herokuapp.com/carStatus/" + vehicle_id);
			URL url = new URL("http://skurt-interview-api.herokuapp.com/carStatus/" + vehicle_id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			 if (conn.getResponseCode() == 200) {
				 is = conn.getInputStream();
			 }
			 return is;
		} catch (Exception e) {
			e.printStackTrace();
			return is;
		}
	}
}
