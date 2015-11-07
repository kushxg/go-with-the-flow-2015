import java.util.List;


public class Vehicle {
	private int max_speed;			// the maximum speed attainable by this kind of car
	private int speed;				// the current speed of this car
	private int length;				// the length of a car
	private DriverLogic logic;
	
	public Vehicle() {
		
	}
	
	public boolean drive(Road road, Vehicle this) {
		return logic.drive(road); 
	}
}
