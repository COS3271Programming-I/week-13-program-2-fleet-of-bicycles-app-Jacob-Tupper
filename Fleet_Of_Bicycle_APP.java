package fleet_of_bicycle_APP;

import java.io.PrintStream;
import java.util.Scanner;
import bicycle_app.Bicycle;

public class Fleet_Of_Bicycle_APP {
	
	static PrintStream so = System.out;
	static Scanner usin = new Scanner(System.in);
	
	//Created a function to change gears
	public static int change_gear(int t_gear) {
		int n_gear;
		so.print("What gear do you want to change? "); n_gear = usin.nextInt();
		
		//Check if the gear is in a valid range
		while (true) {
			if(n_gear <= t_gear) {
				return n_gear;
			}
			else {
				so.print("Gear selection is outside the Cassette range\nPlease select a different gear: ");
				n_gear = usin.nextInt();
				continue;
			}
		}
	}
	
	//Check if Bike needs a tune up
	public static void tune_up_check(float tune_date) {
		if (tune_date > 6) {
			so.println("Bike needs a tune up");
		}
		else so.println("Bike is still good to go");
	}
	
	//Tracks how long ago the last tune up was
	public static float ride(float l_tune) {
		//Created variables
		int days = 0;
		//Collecting the number of days used per ride
		so.print("Enter total number of days Spent riding: "); days = usin.nextInt();
		return days/28;	
	}
	//Creates fleet array
	public static Bicycle[] fleet(int size) {
		//Creating Variables
		int cadence, gear, speed, gear_total, tune_date;
		String owner, type;
		Bicycle[] fleet = new Bicycle[size];
		
		//Created a for loop for the fleet
		for (int i = 0; i < size; i++) {
			//Retrieving the Bike information
			so.println("Enter Name, Bike Model, Total number of Gears, Prefered Gear, \nLast time it was Tuned, Average Speed, and Average Cadence.");
			so.print("Owner: "); owner = usin.next();
			so.print("Bike Model: "); type = usin.next();
			so.print("Total number of Gears: "); gear_total = usin.nextInt();
			so.print("Prefered Gear: "); gear = usin.nextInt();
			so.print("Last Tune in Months: "); tune_date = usin.nextInt();
			so.print("Average Speed: "); speed = usin.nextInt();
			so.print("Average Cadence: "); cadence = usin.nextInt();
			fleet[i] = new Bicycle(cadence, speed, gear, tune_date, gear_total, type, owner);
		}
		return fleet;
	}
	//Printing the Fleet info, as well if the user wants to switch
	public static int fleet_info(Bicycle[] fleet, int size, int o_bike) {
		int swich;
		//Print information
		for (int i = 0; i<size; i++) {
			so.println("\n\n" + fleet[i].getInfo());
		}
		//Selecting new bike if wanted to
		so.print("Would you like to change bikes? (0(no) or 1(yes)): "); swich = usin.nextInt();
		if (swich == 1) {
			so.printf("Which bike would you like to change to (1-%d), old selection was bike %d?", size, o_bike+1);
			return usin.nextInt();
		}
		else return o_bike;
	}
	
	public static void main(String[] args) {
		//Created variables
		int m_size = 0, selection = 0, sel_bike = 0;
		so.print("Enter Fleet Size: "); m_size = usin.nextInt();
		Bicycle[] Bike = new Bicycle[m_size];
		
		//Creating Bike Object array
		Bike = fleet(m_size);
		//Printing the options and selecting which bike to interact with
		for (int i = 0; i < m_size; i++) {
			so.println("Bike: " + Math.addExact(i, 1) + "-Owner: " + Bike[i].owner + "-Model: "+ Bike[i].model);
		}
		so.printf("Select Bike (1-%d): ", m_size); 
		sel_bike = usin.nextInt() - 1;
		
		
		//Created a tag to break the loop
		Whileloop1:
		while(true) {
			so.print("\n1. Change Gears\n2. Check if Bike needs a tune up\n3. Ride Bike\n4. List Fleet or Switch Bike\nChoice: "); selection = usin.nextInt();
			//Switch case to check options
			switch(selection) {
			case(1) : {Bike[sel_bike].gear = change_gear(Bike[sel_bike].total_g);so.println("Gear Switched to " + Bike[sel_bike].gear);break;}
			case(2) : {tune_up_check(Bike[sel_bike].tune_up); break;}
			case(3) : {Bike[sel_bike].tune_up = ride(Bike[sel_bike].tune_up);break;}
			case(4) : {sel_bike = fleet_info(Bike, m_size, sel_bike);break;}
			default : {so.print("Thank you for riding!"); break Whileloop1;} //If anything else is entered the loop breaks
			}
		}
	}
}
	

