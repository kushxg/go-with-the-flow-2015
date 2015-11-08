
public class TestDriver extends DriverLogic {

	public TestDriver(Vehicle v) {
		type="test";
		v.speed = (int)(.7*v.max_speed);  
		
	}
	
	public void drive(Road road, Vehicle v) {
		
		double congT = 0;	// get congestion value from road
		double cong1 = 0;	// congestion in front
		double cong2 = 0;	// congestion in front in other lane
		double cong3 = 0;	// congestion in back in other lane
		double cong4 = 0;	// congestion in back
		
		double vel1 = 0;	// congestion in front
		double vel2 = 0;	// congestion in front in other lane
		double vel3 = 0;	// congestion in back in other lane
		double vel4 = 0;	// congestion in back
		
		int cars = road.vehicles.size();
		
		for (Vehicle c: road.vehicles) {
			if (road.vehicles.indexOf(v) > road.vehicles.indexOf(c)) {	// current vehicle is behind the test vehicle
				if (v.findLane(road) == c.findLane(road)) {
					vel4+=c.speed;
				} else {
					vel3+=c.speed;
				}
			} else if (road.vehicles.indexOf(v) < road.vehicles.indexOf(c)) {	// current vehicle is in front of the test vehicle
				if (v.findLane(road) == c.findLane(road)) {
					vel1+=c.speed;
				} else {
					vel2+=c.speed;
				}
			}
		}
		
		vel1 /= cars;
		vel2 /= cars;
		vel3 /= cars;
		vel4 /= cars;
		
		int frontLen = 3000 - v.findIndex(road);
		int backLen = v.findIndex(road);
		
		cong1 = congestion(vel1,cars,frontLen,1);
		cong2 = congestion(vel2,cars,frontLen,1);
		cong3 = congestion(vel3,cars,backLen,1);
		cong4 = congestion(vel4,cars,backLen,1);
		
		// all congestion values have been computed and stored at this point
		
		double currCongDiff = congDiff(congT, cong1, cong2, cong3, cong4);
		
		
		
		
		
		
		
		
		
		// old stuff
		accel(road,v);
		
		int delta = (int)Math.ceil(mps(v.speed))+1;
		move(delta, road, v);
			
		mergeLeft(road, v);
		mergeRight(road, v);
	}
	
	public double congDiff(double ct, double c1, double c2, double c3, double c4) {
		return Math.abs(ct-c1) + Math.abs(ct-c2) + Math.abs(ct-c3) + Math.abs(ct-c4);
	}
}
