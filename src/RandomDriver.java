
public class RandomDriver extends DriverLogic {

	public RandomDriver(Vehicle v) {
		anger=0;
		type="random";
		v.speed = (int)(v.max_speed*.6);
		
	}
	
	public boolean drive(Road road, Vehicle v) {
		
		int delta = (int)Math.ceil(mps(v.speed))+1;
		move(delta, road, v);
		
		return false;	// false implies that a crash did not take place
	}
	
	public double mps(int speed) {	// converts speed (kph) to m/s
		return speed*0.277778;
	}
}
