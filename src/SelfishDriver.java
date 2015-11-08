
public class SelfishDriver extends DriverLogic{

	public SelfishDriver(Vehicle v) {
		anger=0;
		type="selfish";
		v.speed = (int)(.6*v.max_speed);     //v.max_speed);
		maxTimeToImpact = (int)Math.ceil(v.max_speed/v.accel)+3;
		slowTime = 4;
		speedTime = 9;
	}
	
	public void drive(Road road, Vehicle v) {
		
		accel(road,v);
		
		int delta = (int)Math.ceil(mps(v.speed))+1;
		move(delta, road, v);

	}
}
