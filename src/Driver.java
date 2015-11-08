import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Driver {
	private static PrintWriter file; // for sets of tests
	private static FileWriter masterFile; // for overall analysis of data
	private static BufferedReader reader;
	
	private static int num_tests = 2;
	
	private static int num_sedans = 2;
	private static int num_vans = 2;
	private static int num_trucks = 4;
	
	// dont change these values
	private static int num_random_drivers = 0;
	private static int num_aggressive_drivers = 0;
	private static int num_conservative_drivers = 0;
	private static int num_slow_drivers = 0;
	private static int num_fast_drivers = 0;
	private static int num_safe_drivers = 0;
	private static int num_selfish_drivers = 0;
	private static int num_fair_drivers = 0;
	private static int num_test_drivers = 0;

	
	public static void main(String[] args) throws IOException {
		// each set of tests gets printed out in file
		
		String text = "";
		try {
			reader = new BufferedReader(new FileReader("master_file.csv"));
			try {
		        StringBuilder builder = new StringBuilder();
		        String line = reader.readLine();
	
		        while (line != null) {
		            builder.append(line);
		            builder.append(System.lineSeparator());
		            line = reader.readLine();
		        }
		        text = builder.toString();
		    } finally {
		        reader.close();
		    }
		} catch(Exception e) {System.out.println("master file read error");}
		
		String temp = "";
		file = null;
		for (int i=1111; i<9999; i++) {
			if (!text.contains(""+i)) {
				temp = ""+i+".csv";
				break;
			}
		}
		try {
			file = new PrintWriter(temp);
		} catch (Exception e) {System.out.println("file writing error");}
		try {
			masterFile = new FileWriter("master_file.csv", true);
		} catch (Exception e) {System.out.println("master file write error");}
		
		// masterFile.append("TEST FILE,# CARS,# SEDANS,# VANS,# TRUCKS,# RANDOM DRIVERS,# AGGRESSIVE DRIVERS,# CONSERVATIVE DRIVERS,# SLOW DRIVERS,# FAST DRIVERS,# SAFE DRIVERS,# SELFISH DRIVERS,# FAIR DRIVERS,# TEST DRIVERS,AVG. TOTAL RUNTIME,AVG. AVG. TIME/CAR,# CRASHES\n");
		
		/* SET UP TESTS */
		
		int num_cars = num_sedans + num_vans + num_trucks;
		int total_runtime = 0;
		double avg_time_per_car = 0;
		
		// beginning of test
		file.println("TOTAL RUNTIME,AVG TIME PER CAR,CRASHED");
		
		num_cars = num_sedans + num_vans + num_trucks;
		
		double avg_runtime = 0.0;
		double avg_avg_time_per_car = 0.0;
		int num_crashes = 0;
		
		for (int i=0; i<num_tests; i++) {
			
			Road r = new Road();
			
			int pos;
			int lane;
			for(int j=0;j<num_sedans;j++){
				pos = (int)(Math.random()*1500)+15;
				lane = (int)(Math.random()*2)+1;
				System.out.println(pos + " " + lane);
				int driverType = (int)(Math.random()*7)+1;
				switch(driverType) {
				case 1:
					if (!r.createVehicle("sedan", "aggressive", pos, lane)) {
						j--;
					}
					num_aggressive_drivers++;
					System.out.println("created aggressive");
					break;
				case 2:
					if (!r.createVehicle("sedan", "conservative", pos, lane)) {
						j--;
					}
					num_conservative_drivers++;
					System.out.println("created conservative");
					break;
				case 3:
					if (!r.createVehicle("sedan", "fair", pos, lane)) {
						j--;
					}
					num_fair_drivers++;
					System.out.println("created fair");
					break;
				case 4:
					if (!r.createVehicle("sedan", "fast", pos, lane)) {
						j--;
					}
					num_fast_drivers++;
					System.out.println("created fast");
					break;
				case 5:
					if (!r.createVehicle("sedan", "safe", pos, lane)) {
						j--;
					}
					num_safe_drivers++;
					System.out.println("created safe");
					break;
				case 6:
					if (!r.createVehicle("sedan", "selfish", pos, lane)) {
						j--;
					}
					num_selfish_drivers++;
					System.out.println("created selfish");
					break;
				case 7:
					if (!r.createVehicle("sedan", "slow", pos, lane)) {
						j--;
					}
					num_slow_drivers++;
					System.out.println("created slow");
					break;
				default:
					if (!r.createVehicle("sedan", "random", pos, lane)) {
						j--;
					}
					num_random_drivers++;
					break;
				}
			}
			
			for(int j=0;j<num_vans;j++){
				pos = (int)(Math.random()*1500)+15;
				lane = (int)(Math.random()*2)+1;
				System.out.println(pos + " " + lane);
				int driverType = (int)(Math.random()*7)+1;
				switch(driverType) {
				case 1:
					if (!r.createVehicle("van", "aggressive", pos, lane)) {
						j--;
					}
					num_aggressive_drivers++;
					break;
				case 2:
					if (!r.createVehicle("van", "conservative", pos, lane)) {
						j--;
					}
					num_conservative_drivers++;
					System.out.println("created conservative");
					break;
				case 3:
					if (!r.createVehicle("van", "fair", pos, lane)) {
						j--;
					}
					num_fair_drivers++;
					System.out.println("created fair");
					break;
				case 4:
					if (!r.createVehicle("van", "fast", pos, lane)) {
						j--;
					}
					num_fast_drivers++;
					System.out.println("created fast");
					break;
				case 5:
					if (!r.createVehicle("van", "safe", pos, lane)) {
						j--;
					}
					num_safe_drivers++;
					System.out.println("created safe");
					break;
				case 6:
					if (!r.createVehicle("van", "selfish", pos, lane)) {
						j--;
					}
					num_selfish_drivers++;
					System.out.println("created selfish");
					break;
				case 7:
					if (!r.createVehicle("van", "slow", pos, lane)) {
						j--;
					}
					num_slow_drivers++;
					System.out.println("created slow");
					break;
				default:
					if (!r.createVehicle("vans", "random", pos, lane)) {
						j--;
					}
					num_random_drivers++;
					break;
				}
			}
			
			for(int j=0;j<num_trucks;j++){
				pos = (int)(Math.random()*1500)+15;
				lane = (int)(Math.random()*2)+1;
				System.out.println(pos + " " + lane);
				int driverType = (int)(Math.random()*7)+1;
				switch(driverType) {
				case 1:
					if (!r.createVehicle("truck", "aggressive", pos, lane)) {
						j--;
					}
					num_aggressive_drivers++;
					break;
				case 2:
					if (!r.createVehicle("truck", "conservative", pos, lane)) {
						j--;
					}
					num_conservative_drivers++;
					System.out.println("created conservative");
					break;
				case 3:
					if (!r.createVehicle("truck", "fair", pos, lane)) {
						j--;
					}
					num_fair_drivers++;
					System.out.println("created fair");
					break;
				case 4:
					if (!r.createVehicle("truck", "fast", pos, lane)) {
						j--;
					}
					num_fast_drivers++;
					System.out.println("created fast");
					break;
				case 5:
					if (!r.createVehicle("truck", "safe", pos, lane)) {
						j--;
					}
					num_safe_drivers++;
					System.out.println("created safe");
					break;
				case 6:
					if (!r.createVehicle("truck", "selfish", pos, lane)) {
						j--;
					}
					num_selfish_drivers++;
					System.out.println("created selfish");
					break;
				case 7:
					if (!r.createVehicle("truck", "slow", pos, lane)) {
						j--;
					}
					num_slow_drivers++;
					System.out.println("created slow");
					break;
				default:
					if (!r.createVehicle("trucks", "random", pos, lane)) {
						j--;
					}
					num_random_drivers++;
					break;
				}
			}
			
			r.orderVehicles();
			
			r.run();
			
			total_runtime = r.total_runtime;
			avg_runtime += total_runtime;
			
			String crash_result = (r.crashed) ? "X" : ""; // TYPE OF DRIVER THAT CRASHES
			if(!r.crashed){
				avg_time_per_car = r.avg_time_per_car;
				avg_avg_time_per_car += avg_time_per_car;
				num_crashes++;
			} 
			
			file.println(total_runtime + "," + avg_time_per_car + "," + crash_result);

		}
		
		avg_runtime = ((double) avg_runtime)/num_tests;
		avg_avg_time_per_car = ((double) avg_avg_time_per_car)/num_tests;
		System.out.println("--Overall Statistics--");
		System.out.println("Average Runtime:" + avg_runtime);
		System.out.println("Average Average Time/Car: " + avg_avg_time_per_car);
		
		
		System.out.println("Total Crashes: " + num_crashes);
		masterFile.append(temp + "," + num_cars + "," + num_sedans + "," + num_vans + "," + num_trucks + "," + num_random_drivers + "," + num_aggressive_drivers + "," + num_conservative_drivers + "," + num_slow_drivers + "," + num_fast_drivers + "," + num_safe_drivers + "," + num_selfish_drivers + "," + num_fair_drivers + "," + num_test_drivers + "," + avg_runtime + "," + avg_avg_time_per_car + "," + num_crashes + "\n");
		
		// end of test
		
		masterFile.close();
		file.close();
	}
	
}
