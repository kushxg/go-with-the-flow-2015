import java.util.List;


public class Vehicle {
	public int max_speed;			// the maximum speed attainable by this kind of car
	public int speed;				// the current speed of this car
	public int length;				// the length of a car
	public DriverLogic logic;
	
	public Vehicle() {
		
	}
	
	public boolean drive(Road road) {
		return logic.drive(road, this);
	}
}
