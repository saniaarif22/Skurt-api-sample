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
	
}
