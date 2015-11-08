
public class ConservativeDriver extends DriverLogic{

	public ConservativeDriver(Vehicle v) {
		anger=0;
		type="conservative";
		v.speed = (int)(.4*v.max_speed);     //v.max_speed);
		maxTimeToImpact = (int)Math.ceil(v.max_speed/v.accel)+4;
		slowTime = 6;
		speedTime = 14;
	}
	
	public void drive(Road road, Vehicle v) {
		
		accel(road,v);
		
		int delta = (int)Math.ceil(mps(v.speed))+1;
		move(delta, road, v);
			
		mergeLeft(road, v);
	}
}
