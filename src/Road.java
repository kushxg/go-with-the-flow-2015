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


	public Road(int vehicles){		// FIX THIS ######################################################
		initLanes();
		for (int i=0; i<vehicles; i++) {
			//int vType = (int)Math.random()*3;
			//int vDriver = 0; //(int)Math.random()*8;
			int pos = (int)Math.random()*1500+15;
			int lane = (int)Math.random()*2+1;
			createVehicle("sedan","random",pos,lane);
		}
		System.out.println(this.vehicles.size());
	}
	
	// road constructor for given vehicle arraylist
	public Road(){
		initLanes();
		createVehicle("sedan","random",35,1);
		createVehicle("sedan","random",16,1);
		createVehicle("sedan","random",8,1);
		createVehicle("sedan","random",3,1);
		
		
		
		//createVehicle("sedan","random",344,1);
		//createVehicle("sedan","random",356,1);
		//createVehicle("sedan","random",123,1);
		//createVehicle("sedan","random",989,1);
		
	}
	
	public void createVehicle(String type, String driver, int pos, int lane) {
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
			return;
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
			System.out.println(time);
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
