
public class DriverLogic {
	
	public int anger;
	public String type;
	
	public DriverLogic (){
		
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
	
	public List<Vehicle> findLane(Road r, Vehicle v) {
		
		if (road.lane1.indexOf(v) != -1) {
			return road.lane1;
		}
		return road.lane2;
	}
}
