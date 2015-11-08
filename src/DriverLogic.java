import java.util.ArrayList;
import java.util.List;


public class DriverLogic {
	
	public int anger;
	public String type;

	public int mergeStatus = 0;
	int maxTimeToImpact;
	int slowTime;
	int speedTime;
	
	public List<String> actions = new ArrayList<String>();
	
	public DriverLogic() {
		
	}
	
	public void drive(Road road, Vehicle v) {

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
	
	public boolean move(int delta, Road road, Vehicle v) {
		int newPos = v.findIndex(road)+delta;
		if (newPos < 3000) {
			return road.moveVehicle(v, findIndex(road, v)+delta, findLane(road, v));
		}
		else {
			v.exited=true;
			v.timeOnRoad = road.time;
			road.removeVehicle(v);
		}
		return false;
	}
	
	public double mps(int speed) {	// converts speed (kph) to m/s
		return speed*0.277778;
	}
	
	public void accel(Road road, Vehicle v) {
		int spacesInFront = 0;
		for (int i=findIndex(road, v)+1; i<3000; i++) {
			if (findLane(road, v) == 1) {
				if (road.lane1.get(i) == null) {
					spacesInFront++;
				} else {
					break;
				}
			}
			else if (findLane(road, v) == 2) {
				if (road.lane2.get(i) == null) {
					spacesInFront++;
				} else {
					break;
				}
			}
		}
		
		int timeToImpact = (int)(spacesInFront/(v.speed+.01));
		if (maxTimeToImpact - timeToImpact < slowTime) {	
			v.speed = v.speed+(int)v.brakeAccel;
			if (v.speed<1) {
				v.speed = 0;
			}
			actions.add("brake");
			// System.out.println("brake");
		} else if (maxTimeToImpact - timeToImpact > speedTime) {
			v.speed = v.speed+(int)v.accel;
			actions.add("accel");
			if (v.speed>v.max_speed) {
				v.speed = v.max_speed;
			}
			// System.out.println("accel");
		}
	}
	
	// default merge-left
	// no merge buffer
	public void mergeLeft(Road road, Vehicle v){
		if(v.findLane(road) == 1){
			return;
		}
		
		int pos = v.findIndex(road);
		
		if(mergeStatus == 0){
			for(int i=pos;i>pos-v.length;i--){
				if(i >= 0 && i < road.roadblock_position && road.lane1.get(i) == null){
					return;
				}
			}
			
			mergeStatus++;
		}else{
			System.out.println(mergeStatus);
			mergeStatus++;
			if(mergeStatus == 4){
				road.moveVehicle(v, pos, 1);
			}
		}
	}
	
	public void mergeRight(Road road, Vehicle v){
		if(v.findLane(road) == 2){
			return;
		}
		
		int pos = v.findIndex(road);
		
		if(mergeStatus == 0){
			for(int i=pos;i>pos-v.length;i--){
				if(i >= 0 && i < road.roadblock_position - 100 && road.lane2.get(i) == null){
					return;
				}
			}
			
			mergeStatus++;
		}else{
			System.out.println(mergeStatus);
			mergeStatus++;
			if(mergeStatus == 4){
				road.moveVehicle(v, pos, 2);
			}
		}
	}
}
