import java.util.ArrayList;
import java.util.List;

public class Road {
	private int num_cars; // number of cars
	private int speed_limit; // speed limit of road
	private int road_length = 3000; // road length - in meters
	private int num_lanes = 2; // number of lanes
	private int roadblock_position = 2500; // position of road-block from beginning of road
	
	private int num_sedans;
	private int num
	
	// lanes
	// each row is a pair of lanes
	private List<List<Vehicle>> road = new ArrayList<List<Vehicle>>();
	
	public Road(){
		// 4 | |X|
		// 3 | | |
		// 2 |X| |
		// 1 | | |
		// 0 |X| |
		
		// sets position of cars
		for(int i=0;i<num_cars;i++){
			Vehicle v = new Sedan();
			
			
		}
	}
	
	public Road(ArrayList<Vehicle>){
		
	}
	
	// sets number of cars
	public void setNumCars(int n){
		this.num_cars = n;
	}
	
	// sets length of road
	public void setRoadLength(int n){
		this.road_length = n;
	}
	
	// set number of lanes
	public void setNumLanes(int n){
		this.num_lanes = n;
	}
	
	// set position of road block
	public void setRoadBlock(int n){
		this.roadblock_position = n;
	}
	
	// increments through each second
	public void nextSecond(){
		
	}
}
