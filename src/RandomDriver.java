
public class RandomDriver extends DriverLogic {

	public RandomDriver(Vehicle v) {
		anger=0;
		type="random";
		v.speed = (int)(v.max_speed*.6);
		
	}
	
	public void drive(Road road, Vehicle v) {
		
		int delta = (int)Math.ceil(mps(v.speed))+1;
		move(delta, road, v);
	}
	
	
}
