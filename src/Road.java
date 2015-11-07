import java.util.ArrayList;
import java.util.List;

public class Road {
	public int num_cars; // number of cars
	public int speed_limit; // speed limit of road
	public int road_length = 3000; // road length - in meters
	public int num_lanes = 2; // number of lanes
	public int roadblock_position = 2500; // position of road-block from beginning of road
	
	public int num_sedans;
	public int num_vans;
	public int num_trucks;
	
	// lanes
	public List<Vehicle> lane1 = new ArrayList<Vehicle>();
	public List<Vehicle> lane2 = new ArrayList<Vehicle>(); // has roadblock
	
	// empty constructor
	public Road(){
		// sets num of cars = num sedans
		num_cars = num_sedans = 50;
		
		// sets position of cars
		for(int i=0;i<num_sedans;i++){
			Vehicle v = new Sedan();
			
			int pos = (int) Math.random()*road_length;
			int lane = (int) Math.random()*2+1;
			boolean works = false;
			while(!works){
				if(lane==1){
					if(lane1.get(pos)==null){lane1.set(pos, v);works=true;}
				}else{
					if(lane2.get(pos)==null){lane2.set(pos, v);works=true;}
				}
			}
			
		}
		
		for(int i=0;i<num_vans;i++){
			Vehicle v = new Van();
			
		}
		
		for(int i=0;i<num_trucks;i++){
			Vehicle v = new Truck();
			
		}
	}
	
	// checks whether car numbers add up
	public boolean checkRoad(){
		return (num_sedans + num_vans + num_trucks == num_cars);
	}
	
	// road constructor for given vehicle arraylist
	public Road(ArrayList<Vehicle> vehicle_list){
		for(Vehicle v: vehicle_list){
			
		}
	}
	
	// increments through each second
	public void nextSecond(){
		
	}
}
