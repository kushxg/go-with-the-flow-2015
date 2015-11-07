import java.util.List;


public class Vehicle {
	public int max_speed;			// the maximum speed attainable by this kind of car (kph)
	public int speed;				// the current speed of this car (kph)
	public int length;				// the length of this car
	public double brakeAccel;		// the braking acceleration of this car
	public double accel;			// the acceleration of this car
	public int mergeTime;			// the time (in seconds) in which this vehicle can merge
	public boolean exited = false;	// whether or not the vehicle is still in the 3km stretch of road
	public DriverLogic logic;
	
	public Vehicle() {
		
	}
	
	
	public boolean drive(Road road) {	// returns whether or not a crash took place
		if (exited) {
			return false;
		}
		return logic.drive(road, this);
	}
	
	public int findIndex(Road road) {
		
		if (road.lane1.indexOf(this) != -1) {
			return road.lane1.indexOf(this)+length-1;
		}
		else {
			return road.lane2.indexOf(this)+length-1;
		}
		
	}
	
	public int findLane(Road road) {
		int lane = 0;
		if (road.lane1.indexOf(this) != -1) {
			lane+=1;
		} if (road.lane2.indexOf(this) != -1) {
			lane+=2;
		}
		return lane;	// returns 1 if car is in lane 1, 2 if lane 2, or 3 if in both lanes   (0 if vehicle doesn't exist)
	}
	
}
