
public class TestDriver extends DriverLogic {

	public TestDriver(Vehicle v) {
		anger=0;
		type="test";
		v.speed = (int)(Math.random()*65);     //v.max_speed);
		
	}
	
	public void drive(Road road, Vehicle v) {
		
		accel(road,v);
		
		int delta = (int)Math.ceil(mps(v.speed))+1;
		move(delta, road, v);
			
		mergeLeft(road, v);
	}
}
