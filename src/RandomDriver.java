
public class RandomDriver extends DriverLogic {

	public RandomDriver(Vehicle v) {
		anger=0;
		type="random";
		v.speed = (int)(Math.random()*65);     //v.max_speed);
		
	}
	
	public boolean drive(Road road, Vehicle v) {
		
		int delta = (int)mps(v.speed);
		move(delta, road, v);
		
		return false;	// false implies that a crash did not take place
	}
	
	public double mps(int speed) {	// converts speed (kph) to m/s
		return speed*0.277778;
	}
}
