
public class SlowDriver extends DriverLogic {
	
	public SlowDriver(Vehicle v) {
		anger=0;
		type="slow";
		v.speed = (int)(Math.random()*65);     //v.max_speed);
		
	}
	
	public boolean drive(Road road, Vehicle v) {
		
		return false;	// false implies that a crash did not take place
	}
}
