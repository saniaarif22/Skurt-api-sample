# Skurt-api-sample
A sample java application that polls the skurt api endpoint to get the location of a car.

It will send an alert within 5 minutes of a car being driven out of a zone that is predefined. 

The service monitors the status of the cars and sends an alert via email if any car is out of their designated zone.

Car Status API Documentation:
URL: http://skurt­interview­api.herokuapp.com/

GET /carStatus/{CarID}

> @param carID: An integer between 1­11 that represents a car’s ID. Cars with ID 1­10 are
valid cars whose position keep changing. Car #11 is a test car that is always out of the
bounding zone.

> Returns GeoJSON with a car’s status (current position as a coordinate)
