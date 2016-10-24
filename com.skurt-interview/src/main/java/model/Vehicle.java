package model;

public interface Vehicle {

	boolean isInRange();
	int getVehicleId();
	Coordinates getCoordinates();
	Polygon getPolygon();

}
