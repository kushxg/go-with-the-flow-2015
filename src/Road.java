import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	public List<Double> total_congestions = new ArrayList<Double>();
	
	// return information
	public int total_runtime; 
	public double avg_time_per_car;
	
	public boolean crashed = false;

	public Road(int vehicles){
		initLanes();
		for (int i=0; i<vehicles; i++) {
			int vType = (int)Math.random()*3+1;
			String type = "";
			switch(vType){
			case 1:
				type = "sedan";
				break;
			case 2:
				type = "van";
				break;
			case 3:
				type = "truck";
				break;
			}
			int vDriver = (int)Math.random()*9+1;
			String driverType = "";
			switch(vDriver){
			case 1:
				driverType = "random";
				break;
			case 2:
				driverType = "aggressive";
				break;
			case 3:
				driverType = "conservative";
				break;
			case 4:
				driverType = "slow";
				break;
			case 5:
				driverType = "fast";
				break;
			case 6:
				driverType = "safe";
				break;
			case 7:
				driverType = "selfish";
				break;
			case 8:
				driverType = "fair";
				break;
			case 9:
				driverType = "test";
				break;
			}
			int pos = (int)Math.random()*1500+15;
			int lane = (int)Math.random()*2+1;
			createVehicle(type,driverType,pos,lane);
		}
	}
	
	// road constructor for given vehicle arraylist
	public Road(){
		initLanes();
	}
	
	public boolean createVehicle(String type, String driver, int pos, int lane) {
		Vehicle newV = null;
		if (type.equals("sedan")) {
			newV = new Sedan();
		}
		else if (type.equals("van")) {
			newV = new Van();
		}
		else if (type.equals("truck")) {
			newV = new Truck();
		}
		if (!placeVehicle(newV, pos, lane)) {
			return false;
		}
		vehicles.add(newV);
		if (driver.equals("random")) {
			newV.logic = new RandomDriver(newV);
		}
		else if (driver.equals("aggressive")) {
			newV.logic = new AggressiveDriver(newV);
		}
		else if (driver.equals("conservative")) {
			newV.logic = new ConservativeDriver(newV);
		}
		else if (driver.equals("slow")) {
			newV.logic = new SlowDriver(newV);
		}
		else if (driver.equals("fast")) {
			newV.logic = new FastDriver(newV);
		}
		else if (driver.equals("safe")) {
			newV.logic = new SafeDriver(newV);
		}
		else if (driver.equals("selfish")) {
			newV.logic = new SelfishDriver(newV);
		}
		else if (driver.equals("fair")) {
			newV.logic = new FairDriver(newV);
		}
		else if (driver.equals("test")) {
			newV.logic = new TestDriver(newV);
		}
		return true;
		
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
			v.crashed = true;
			return false;	// crash occurred
		}
		return true;
	}
	
	public boolean orderVehicles() {
		// sorts vehicles in descending order
		Collections.sort(vehicles, new CustomComparator());
		return true;
	}
	
	public void run() {
		
		while (nextSecond().equals("running")) {
			time++;
		}
		
		total_runtime = time;
		System.out.println("Total Runtime: "+time);
		
		avg_time_per_car = 0;
		for (Vehicle v: vehicles) {
			avg_time_per_car += v.timeOnRoad;
		}
		avg_time_per_car = avg_time_per_car/vehicles.size();
		System.out.println("Avg. Time/Car: "+ avg_time_per_car);
		
		System.out.println("ct size: " + total_congestions.size());
		
		// calculate everyone's change in anger
		for(Vehicle v: vehicles){
			double delta_a = 0;
			for(int i=0;i<total_congestions.size();i++){
				delta_a += (v.subset_congestions.get(i)-total_congestions.get(i));
			}
			v.anger = delta_a; // more congestion, higher anger
			
			System.out.println(v.anger);
		}
	}
	
	// increments through each second
	public String nextSecond(){
		orderVehicles(); // sorts vehicles in descending order
		
		// congestion calculations
		double avg_velocity = 0;
		for(Vehicle w: vehicles){
			avg_velocity += w.speed;
		}
		avg_velocity = ((double) avg_velocity)/vehicles.size();
		double ct = congestion(avg_velocity, vehicles.size(), 3000, 2);
		
		total_congestions.add(ct);
		
		for (Vehicle v: vehicles) {
			// calculate Cs
			int num_cars_range = 0;
			double avg_velocity_range = 0;
			
			// loop range of 100 around car
			for(int i=v.findIndex(this)-50;i<v.findIndex(this)+50;i++){
				if(i >= 0 && i < 3000){
					if(lane1.get(i) != null && !(lane1.get(i) instanceof RoadBlock)){
						num_cars_range++;
						avg_velocity_range += lane1.get(i).speed;
					}
					
					if(lane2.get(i) != null && !(lane2.get(i) instanceof RoadBlock)){
						num_cars_range++;
						avg_velocity_range += lane2.get(i).speed;
					}
				}
			}
			
			if(num_cars_range != 0){
				avg_velocity_range = ((double) avg_velocity_range)/num_cars_range;
			}else{
				avg_velocity_range = 0;
			}
			
			double cs = congestion(avg_velocity_range, num_cars_range, 100, 2);
			v.subset_congestions.add(cs);
			
			if (v.drive(this)) {	// if a crash took place, then break out of the simulation
				crashed = true;
				return "crash";
			}
		}
		if (allCarsExited()) {
			// set anger values
			return "done";
		}
		return "running";		// implies simulation is still running in this second
	}
	
	public boolean allCarsExited() {
		for (Vehicle v: vehicles) {
			if (!v.exited && !v.crashed)
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
	
	public double congestion(double vel, int cars, int len, int lanes) {
		return vel*vel*cars/len/lanes/2;
	}

	private class CustomComparator implements Comparator<Vehicle> {
	    @Override
	    public int compare(Vehicle v1, Vehicle v2) {
	        return Integer.valueOf(v2.findIndex(Road.this)).compareTo(Integer.valueOf(v1.findIndex(Road.this))); // descending order
	    }
	}
}


