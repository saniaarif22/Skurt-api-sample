package service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.Car;
import model.Coordinates;
import model.Polygon;
import model.Vehicle;

public class ParserService {
	public Vehicle parse(int vehicle_id, InputStream in) {
		Coordinates coordinates = null;
		Polygon polygon = null;
		if (in == null) 
			return new Car(vehicle_id, coordinates, polygon);
		ArrayList<Coordinates> polygonCoordinates = new ArrayList<Coordinates>();
		try {
		JSONParser jsonParser = new JSONParser();
		
		JSONArray featuresArray = (JSONArray) ((JSONObject)jsonParser
												.parse(new InputStreamReader(in, "UTF-8")))
												.get("features");

		JSONObject point = (JSONObject) jsonParser
							.parse(((JSONObject)jsonParser
							.parse(featuresArray
							.get(0).toString()))
							.get("geometry")
							.toString());

		JSONArray coodArray = (JSONArray)jsonParser
							.parse(point
							.get("coordinates").toString());
			
		JSONObject polygonObject = (JSONObject) jsonParser
							.parse(((JSONObject)jsonParser
							.parse(featuresArray
							.get(1).toString()))
							.get("geometry")
							.toString());
		
		JSONArray pointArray = (JSONArray)jsonParser
							.parse(polygonObject
							.get("coordinates").toString());
		
		JSONArray coordsArray = (JSONArray)jsonParser.parse(pointArray.get(0).toString());
		

		} catch (Exception e) {
			/* Any JSON parse errors */
			e.printStackTrace();
		}
	}
	
	private Coordinates extractCoordinates (JSONArray coodArray) {
		double co_od_x = (Double) (coodArray).get(0);
		double co_od_y = (Double) (coodArray).get(1);
		/* System.out.println("X: " + co_od_x + " Y: " + co_od_y); */
		return new Coordinates(co_od_x, co_od_y);
		
	}

}
