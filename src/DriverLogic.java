
public class DriverLogic {
	
	public int anger;
	public String type;
	
	public DriverLogic (){
		
	}
	
	public boolean drive(Road road, Vehicle v) {
		
		return false;	// false implies that a crash did not take place
	}
	
	public int findThisVehicle(Road road, Vehicle v) {
		
		return road.lane1.indexOf(this)+v.length-1;
		
	}
}
