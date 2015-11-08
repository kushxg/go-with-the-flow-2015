
public class FastDriver extends DriverLogic {

	public FastDriver(Vehicle v) {
		anger=0;
		type="fast";
		v.speed = (int)(.6*v.max_speed);
		maxTimeToImpact = (int)Math.ceil(v.max_speed/v.accel)+1;
		slowTime = 3;
		speedTime = 8;
	}
	
	public void drive(Road road, Vehicle v) {
		
		accel(road,v);
		
		int delta = (int)Math.ceil(mps(v.speed))+1;
		move(delta, road, v);

	}
}
