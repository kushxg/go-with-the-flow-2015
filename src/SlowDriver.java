
public class SlowDriver extends DriverLogic {
	
	public SlowDriver(Vehicle v) {
		anger=0;
		type="slow";
		v.speed = (int)(.2*v.max_speed);     //v.max_speed);
		maxTimeToImpact = (int)Math.ceil(v.max_speed/v.accel)+6;
		slowTime = 8;
		speedTime = 16;
	}
	
	public void drive(Road road, Vehicle v) {
		
		accel(road,v);
		
		int delta = (int)Math.ceil(mps(v.speed))+1;
		move(delta, road, v);

	}
}
