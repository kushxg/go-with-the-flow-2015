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
	public int num_truck;
	
	// lanes
	// each row is a pair of lanes
	// 4 | |X|
	// 3 | | |
	// 2 |X| |
	// 1 | | |
	// 0 |X| |
	private List<List<Vehicle>> road = new ArrayList<List<Vehicle>>();
	
	public Road(){
		// sets position of cars
		for(int i=0;i<num_cars;i++){
			Vehicle v = new Sedan();
			
			
		}
	}
	
	public Road(ArrayList<Vehicle> vehicle_list){
		for(Vehicle v: vehicle_list){
			
		}
	}
	
	// increments through each second
	public void nextSecond(){
		
	}
}
