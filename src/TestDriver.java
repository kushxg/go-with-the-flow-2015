
public class TestDriver extends DriverLogic {

	public TestDriver(Vehicle v) {
		anger=0;
		type="test";
		v.speed = (int)(Math.random()*65);     //v.max_speed);
		
	}
	
	public boolean drive(Road road, Vehicle v) {
		
		return false;	// false implies that a crash did not take place
	}
}
