
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
		double congDiff1 = congDiff(congT, congestion((vel1*cars+v.speed)/cars,cars,frontLen-v.speed,1), cong2, cong3, cong4);
		double congDiff2 = congDiff(congT, cong1, congestion((vel2*cars+v.speed)/cars,cars,frontLen-v.speed,1), cong3, cong4);
		double congDiff3 = congDiff(congT, cong1, cong2, congestion((vel3*cars+v.speed)/cars,cars,backLen+v.speed,1), cong4);
		double congDiff4 = congDiff(congT, cong1, cong2, cong3, congestion((vel4*cars+v.speed)/cars,cars,backLen+v.speed, 1));
		
		double minValue = Math.min(Math.min(congDiff1,congDiff2),Math.min(Math.min(congDiff3,congDiff4),currCongDiff));
		
		if (minValue == currCongDiff) {
			actions.add("stay");
		} else if (minValue == congDiff1) {
			actions.add("accel");
			v.speed+=v.accel;
		} else if (minValue == congDiff2) {
			if (v.findLane(road) == 1) {
				actions.add("attempt move right");
				attemptMergeRight();
			} else {
				attemptMergeLeft();
			}
		} else if (minValue == congDiff3) {
			actions.add("move back and merge");
			v.speed+=v.brakeAccel;
			if (v.findLane(road) == 1) {
				attemptMergeRight();
			} else {
				attemptMergeLeft();
			}
		} else if (minValue == congDiff4) {
			actions.add("brake");
			v.speed+=v.brakeAccel;
		}
		
		int delta = (int)Math.ceil(mps(v.speed))+1;
		move(delta, road, v);
	}
	
	public void attemptMergeRight() {
		
	}
	
	public void attemptMergeLeft() {
		
	}
	
	public double congestion(double vel, int cars, int len, int lanes) {
		return vel*vel*cars/len/lanes/2;
	}
	
	public double congDiff(double ct, double c1, double c2, double c3, double c4) {
		return Math.abs(ct-c1) + Math.abs(ct-c2) + Math.abs(ct-c3) + Math.abs(ct-c4);
	}
}
