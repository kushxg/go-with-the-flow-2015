
public class SafeDriver extends DriverLogic {

	public SafeDriver(Vehicle v) {
		anger=0;
		type="Safe";
		v.speed = (int)(.4*v.max_speed);     //v.max_speed);
		maxTimeToImpact = (int)Math.ceil(v.max_speed/v.accel)+2;
		slowTime = 4;
		speedTime = 10;
		
	}
	
	public void drive(Road road, Vehicle v) {
		accel(road,v);
		
		int delta = (int)Math.ceil(mps(v.speed))+1;
		move(delta, road, v);
		
		mergeLeft(road, v);
	}
}
