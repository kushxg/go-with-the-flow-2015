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
		int num_sedans = 0, num_vans = 0, num_trucks = 0;
		int num_cars = num_sedans + num_vans + num_trucks;
		int num_random_drivers = 0, num_aggressive_drivers = 0, num_conservative_drivers = 0, num_slow_drivers = 0, num_fast_drivers = 0, num_safe_drivers = 0, num_selfish_drivers = 0, num_fair_drivers = 0, num_test_drivers = 0;
		int total_runtime = 0;
		double avg_time_per_car = 0;
		
		// beginning of test
		file.println("TOTAL RUNTIME,AVG TIME PER CAR,CRASHED");
		
		int num_tests = 5; // number of tests
		
		Road r = new Road();
		
		num_sedans = 50;
		num_cars = num_sedans + num_vans + num_trucks;
		
		num_random_drivers = 50;
		
		double avg_runtime = 0;
		double avg_avg_time_per_car = 0;
		int num_crashes = 0;
		
		// loop over num_tests ############################ add loop into code
		int pos;
		int lane;
		for(int i=0;i<num_sedans;i++){
			pos = (int)(Math.random()*1500)+15;
			lane = (int)(Math.random()*2)+1;
			System.out.println(pos + " " + lane);
			r.createVehicle("sedan", "random", pos, lane);
		}
		
		r.orderVehicles();
		
		r.run();
		
		total_runtime = r.total_runtime;
		avg_runtime += total_runtime;
		avg_time_per_car = r.avg_time_per_car;
		avg_avg_time_per_car += avg_time_per_car;
		
		String crash_result = (r.crashed) ? "X" : ""; // TYPE OF DRIVER THAT CRASHES
		if(r.crashed) num_crashes++;
		
		file.println(total_runtime + "," + avg_time_per_car + "," + crash_result);
		
		// end loop
		
		avg_runtime = ((double) avg_runtime) / num_tests;
		avg_avg_time_per_car /= num_tests;
		
		masterFile.append(temp + "," + num_cars + "," + num_sedans + "," + num_vans + "," + num_trucks + "," + num_random_drivers + "," + num_aggressive_drivers + "," + num_conservative_drivers + "," + num_slow_drivers + "," + num_fast_drivers + "," + num_safe_drivers + "," + num_selfish_drivers + "," + num_fair_drivers + "," + num_test_drivers + "," + avg_runtime + "," + avg_avg_time_per_car + "," + num_crashes + "\n");
		
		// end of test
		
		masterFile.close();
		file.close();
	}
	
}
