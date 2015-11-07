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

	// arguments - # SEDANS, # VANS, # TRUCKS
	public Road(int nsedans, int nvans, int ntrucks){
		
		initLanes();
		// sets num of cars = num sedans
		num_sedans = nsedans;
		num_vans = nvans;
		num_trucks = ntrucks;
		num_cars = num_sedans + num_vans + num_trucks;
		
		// sets position of sedans
		for(int i=0;i<num_sedans;i++){
			Vehicle v = new Sedan();
			v.logic = new RandomDriver(v);
			vehicles.add(v);
			
			randomPlaceVehicle(v);
			
		}
		
		// sets position of vans
		for(int i=0;i<num_vans;i++){
			Vehicle v = new Van();
			vehicles.add(v);
			
			randomPlaceVehicle(v);
		}
		
		// sets position of trucks
		for(int i=0;i<num_trucks;i++){
			Vehicle v = new Truck();
			vehicles.add(v);
			
			randomPlaceVehicle(v);
		}
	}
	
	// road constructor for given vehicle arraylist
	public Road(ArrayList<Vehicle> vehicle_list){
		initLanes();
		for(Vehicle v: vehicle_list){
			vehicles.add(v);
			
			randomPlaceVehicle(v);
		}
	}
	
	public Road() {
		initLanes();
		Vehicle car = new Sedan();
		placeVehicle(car, 3, 1);
		vehicles.add(car);
		car.logic = new RandomDriver(car);
		
		Vehicle car2 = new Sedan();
		placeVehicle(car2, 3, 2);
		vehicles.add(car2);
		car2.logic = new RandomDriver(car2);
	}
	
	public void randomPlaceVehicle(Vehicle v) {
		int pos = (int) Math.random()*roadblock_position;
		int lane = (int) Math.random()*2+1;
		boolean works = false;
		while(!works){
			works=placeVehicle(v, pos, lane);
			
			// if don't work
			pos = (int) Math.random()*roadblock_position;
			lane = (int) Math.random()*2+1;
		}
	}
	
	public boolean placeVehicle(Vehicle v, int pos, int lane) {		// places a vehicle at a specific position (based off of front index) if the vehicle goes off the array, those elements are left out
		
		if (lane==1 && lane1.get(pos) == null) {
			for (int i=0; i<v.length; i++) {
				if (pos-i >= 0) {
					lane1.set(pos-i, v);
				}
			}
			return true;
		}
		else if (lane==2 && lane2.get(pos) == null) {
			for (int i=0; i<v.length; i++) {
				if (pos-i >= 0) {
					lane2.set(pos-i, v);
				}
			}
			return true;
		}
		else if (lane==3 && lane1.get(pos) == null && lane2.get(pos) == null) {
			for (int i=0; i<v.length; i++) {
				if (pos-i >= 0) {
					lane1.set(pos-i, v);
					lane2.set(pos-i, v);
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public void removeVehicle(Vehicle v) {
		while (lane1.indexOf(v) != -1) {
			lane1.set(lane1.indexOf(v), null);
		}
		while (lane2.indexOf(v) != -1) {
			lane2.set(lane2.indexOf(v), null);
		}
	}
	
	public boolean moveVehicle(Vehicle v, int pos, int lane) {
		removeVehicle(v);
		if (!placeVehicle(v,pos,lane)) {
			return false;	// crash occurred
		}
		return true;
	}
	
	public boolean orderVehicles() {
		// orders vehicles array based on index of each vehicle
		return true;
	}
	
	public void run() {
		
		while (nextSecond().equals("running")) {
			time++;
		}
		
		System.out.println("Total Runtime: "+time);
		System.out.println("Avg. Time/Car: "+(((double)time)/vehicles.size()));
	}
	
	// increments through each second
	public String nextSecond(){
		for (Vehicle v: vehicles) {
			if (v.drive(this)) {	// if a crash took place, then break out of the simulation
				return "crash";
			}
		}
		if (allCarsExited()) {
			return "done";
		}
		return "running";		// implies simulation is still running in this second
	}
	
	public boolean allCarsExited() {
		for (Vehicle v: vehicles) {
			if (!v.exited)
				return false;
		}
		return true;
	}
	
	public void initLanes() {
		for (int i=0; i<3000; i++) {
			lane1.add(null);
			lane2.add(null);
		}
		
	}
}
