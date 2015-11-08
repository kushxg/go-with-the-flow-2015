
public class AggressiveDriver extends DriverLogic{

	
	
	public AggressiveDriver(Vehicle v) {
		type="aggressive";
		v.speed = (int)(.6*v.max_speed);
		maxTimeToImpact = (int)Math.ceil(v.max_speed/v.accel)+1;
		slowTime = 3;
		speedTime = 8;
	}
	
	
	public void drive(Road road, Vehicle v) {
		
		accel(road,v);
		
		int delta = (int)Math.ceil(mps(v.speed))+1;
		move(delta, road, v);
		
		mergeLeft(road, v);
	}
	
	// custom merge-left
	// merge buffer of 0
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
			if(mergeStatus == 3){
				road.moveVehicle(v, pos, 1);
			}
		}
	}
}
