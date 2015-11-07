import java.util.ArrayList;
import java.util.List;

// 2 constructors
// arguments: num of each type of car, list of vehicles

public class Road {
	public int num_cars; // number of cars
	public int speed_limit = 65; // speed limit of road
	public int road_length = 3000; // road length - in meters
	public int num_lanes = 2; // number of lanes
	public int roadblock_position = 2500; // position of road-block from beginning of road
	
	// add up to num_cars
	public int num_sedans = 0;
	public int num_vans = 0;
	public int num_trucks = 0;
	
	// lanes
	public List<Vehicle> lane1 = new ArrayList<Vehicle>();
	public List<Vehicle> lane2 = new ArrayList<Vehicle>(); // has roadblock
	
	// vehicle array
	public List<Vehicle> vehicles = new ArrayList<Vehicle>(); // adds all vehicles created
	
	// universal time for simulation
	public int time = 0;
	
	// empty constructor
	// arguments - # SEDANS, # VANS, # TRUCKS
	public Road(int nsedans, int nvans, int ntrucks){
		// sets num of cars = num sedans
		num_sedans = nsedans;
		num_vans = nvans;
		num_trucks = ntrucks;
		num_cars = num_sedans + num_vans + num_trucks;
		
		// sets position of sedans
		for(int i=0;i<num_sedans;i++){
			Vehicle v = new Sedan();
			vehicles.add(v);
			
			// random positions
			int pos = (int) Math.random()*roadblock_position;
			int lane = (int) Math.random()*2+1;
			boolean works = false;
			while(!works){
				if(lane==1){
					if(lane1.get(pos)==null){lane1.set(pos, v);works=true;} // set vehicle position
				}else{
					if(lane2.get(pos)==null){lane2.set(pos, v);works=true;} // set vehicle position
				}
				
				// if don't work
				pos = (int) Math.random()*roadblock_position;
				lane = (int) Math.random()*2+1;
			}
			
		}
		
		// sets position of vans
		for(int i=0;i<num_vans;i++){
			Vehicle v = new Van();
			vehicles.add(v);
			
			// random positions
			int pos = (int) Math.random()*roadblock_position;
			int lane = (int) Math.random()*2+1;
			boolean works = false;
			while(!works){
				if(lane==1){
					if(lane1.get(pos)==null){lane1.set(pos, v);works=true;} // set vehicle position
				}else{
					if(lane2.get(pos)==null){lane2.set(pos, v);works=true;} // set vehicle position
				}
				
				// if don't work
				pos = (int) Math.random()*roadblock_position;
				lane = (int) Math.random()*2+1;
			}
		}
		
		// sets position of trucks
		for(int i=0;i<num_trucks;i++){
			Vehicle v = new Truck();
			vehicles.add(v);
			
			// random positions
			int pos = (int) Math.random()*roadblock_position;
			int lane = (int) Math.random()*2+1;
			boolean works = false;
			while(!works){
				if(lane==1){
					if(lane1.get(pos)==null){lane1.set(pos, v);works=true;} // set vehicle position
				}else{
					if(lane2.get(pos)==null){lane2.set(pos, v);works=true;} // set vehicle position
				}
				
				// if don't work
				pos = (int) Math.random()*roadblock_position;
				lane = (int) Math.random()*2+1;
			}
		}
	}
	
	// road constructor for given vehicle arraylist
	public Road(ArrayList<Vehicle> vehicle_list){
		for(Vehicle v: vehicle_list){
			vehicles.add(v);
			
			// random positions
			int pos = (int) Math.random()*roadblock_position;
			int lane = (int) Math.random()*2+1;
			boolean works = false;
			while(!works){
				if(lane==1){
					if(lane1.get(pos)==null){lane1.set(pos, v);works=true;} // set vehicle position
				}else{
					if(lane2.get(pos)==null){lane2.set(pos, v);works=true;} // set vehicle position
				}
				
				// if don't work
				pos = (int) Math.random()*roadblock_position;
				lane = (int) Math.random()*2+1;
			}
		}
	}
	
	// increments through each second
	public void nextSecond(){
		
	}
}
