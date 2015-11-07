
public class RandomDriver extends DriverLogic {

	public RandomDriver(Vehicle v) {
		anger=0;
		type="random";
		v.speed = (int)(Math.random()*v.max_speed);
		
	}
	
	public boolean drive(Road road, Vehicle v) {
		
		
		
		return false;	// false implies that a crash did not take place
	}
}
