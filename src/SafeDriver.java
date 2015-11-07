
public class SafeDriver extends DriverLogic {

	public SafeDriver(Vehicle v) {
		anger=0;
		type="Safe";
		v.speed = (int)(Math.random()*65);     //v.max_speed);
		
	}
	
	public boolean drive(Road road, Vehicle v) {
		
		return false;	// false implies that a crash did not take place
	}
}
