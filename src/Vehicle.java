import java.util.List;


public class Vehicle {
	public int max_speed;			// the maximum speed attainable by this kind of car
	public int speed;				// the current speed of this car
	public int length;				// the length of this car
	public double brakeAccel;		// the braking acceleration of this car
	public double accel;			// the acceleration of this car
	public int mergeTime;			// the time (in seconds) in which this vehicle can merge
	public DriverLogic logic;
	
	public Vehicle() {
		
	}
	
	public boolean drive(Road road) {
		return logic.drive(road, this);
	}
}
