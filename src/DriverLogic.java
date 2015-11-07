import java.util.List;


public class DriverLogic {
	
	public int anger;
	public String type;
	
	public boolean isAccel = false;
	public boolean isBraking = false;
	public int mergeStatus = 0;
	
	public DriverLogic() {
		
	}
	
	public boolean drive(Road road, Vehicle v) {
		
		return false;	// false implies that a crash did not take place
	}
	
	public int findIndex(Road road, Vehicle v) {
		
		if (road.lane1.indexOf(v) != -1) {
			return road.lane1.indexOf(v)+v.length-1;
		}
		else {
			return road.lane2.indexOf(v)+v.length-1;
		}
		
	}
	
	public int findLane(Road road, Vehicle v) {
		int lane = 0;
		if (road.lane1.indexOf(v) != -1) {
			lane+=1;
		} if (road.lane2.indexOf(v) != -1) {
			lane+=2;
		}
		return lane;	// returns 1 if car is in lane 1, 2 if lane 2, or 3 if in both lanes   (0 if vehicle doesn't exist)
	}
}
