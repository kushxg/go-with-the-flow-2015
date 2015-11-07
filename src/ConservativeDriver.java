
public class ConservativeDriver extends DriverLogic{

	public ConservativeDriver(Vehicle v) {
		anger=0;
		type="conservative";
		v.speed = (int)(Math.random()*65);     //v.max_speed);
		
	}
	
	public boolean drive(Road road, Vehicle v) {
		
		return false;	// false implies that a crash did not take place
	}
}
