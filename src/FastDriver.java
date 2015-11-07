
public class FastDriver extends DriverLogic {

	public FastDriver(Vehicle v) {
		anger=0;
		type="fast";
		v.speed = (int)(Math.random()*65);     //v.max_speed);
		
	}
	
	public boolean drive(Road road, Vehicle v) {
		
		return false;	// false implies that a crash did not take place
	}
}
