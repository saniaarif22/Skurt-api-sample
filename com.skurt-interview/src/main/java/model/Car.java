package model;

public class Car implements Vehicle {
	int car_id;
	Coordinates co_od;
	Polygon poly;
	
	public Car(int car_id, Coordinates co_od, Polygon poly) {
		this.car_id = car_id;
		this.co_od = co_od;
		this.poly = poly;		
	}
	
	public boolean isInRange() {
		/* In case incomplete response was received */
		if (co_od == null || poly == null) 
			return false;
		if (contains(poly, co_od))
			return true;
		return false;
	}

	/* Check if given coordinates are in the range polygon */
	private boolean contains(Polygon poly, Coordinates co_od) {
		int i, j;
		boolean result = false;
		for(i = 0, j = poly.vertices.size() - 1; i < poly.vertices.size(); j = i++) {
			if((poly.vertices.get(i).y > co_od.y) != (poly.vertices.get(j).y > co_od.y) &&
					(co_od.x < (poly.vertices.get(j).x - poly.vertices.get(i).x) * 
							(co_od.y - poly.vertices.get(i).y) / 
							(poly.vertices.get(j).y - poly.vertices.get(i).y) 
							+ poly.vertices.get(i).x)) {
				result = !result;
			}
		}
		return result;
	}

	public int getVehicleId() {
		return car_id;
	}

	public Coordinates getCoordinates() {
		return co_od;
	}

	public Polygon getPolygon() {
		return poly;
	}
}
