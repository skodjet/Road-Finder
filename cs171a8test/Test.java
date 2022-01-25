/*THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS 
 * OR COPIED FROM ONLINE RESOURCES. Tommy Skodje*/

package cs171a8test;

import java.io.FileInputStream;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Test { //Java did not allow the file to be called "Test-1", so I renamed it "Test"
	
    //TODO 1: Read the file into appropriate data structures
    //for both cities and roads.
	public static void main(String[] args) {
		
		City newCity;
		int numOfCities = 0;
		int numOfEdges = 0;
		
		
		//The cities .dat file should be input as the first argument, and the roads .dat file as the second argument. 
		if (args.length < 1) {
			System.out.println("Please input a file");
		}
		
		String citiesFile = args[0];
		String roadsFile = args[1];
		
		
		//Find the number of cities
		try {
		Scanner numsScanner = new Scanner(new FileInputStream(citiesFile));
		while (numsScanner.hasNext()) {
			numOfCities++;
			numsScanner.nextLine();
		}
		
		} catch (FileNotFoundException a) {
			System.out.println("City File not found");
			System.exit(-1);
		}
		
		
		 //Create an adjacency list.
		 LinkedList<Road>adjList[] = new LinkedList[numOfCities];
		 
		
		
		LinkedList<City> citiesList = new LinkedList<City>();
		
		//Reads the .dat file with city and create a new city with that city's information
		try {
			Scanner citiesScanner = new Scanner(new FileInputStream(citiesFile));
			while (citiesScanner.hasNext()) {
				String line = citiesScanner.nextLine();
				String[] fields = line.split("(\s{2,})"); //Uses regular expressions to split each character sequence in the city file.
				
				
				
				//Records the number, city code, full city name, population, and elevation of the city from the array fields[].
				int number = Integer.parseInt(fields[0].trim());
				String city_code = fields[1].trim();
				String full_city_name = fields[2].trim();
				int population = Integer.parseInt(fields[3].trim());
				int elevation = Integer.parseInt(fields[4].trim());
				
				//Create a new city with this info.
				newCity = new City(number, city_code, full_city_name, population, elevation);
				
				//Add the city to the citiesList Linked List.
				citiesList.add(newCity);
				
			}
			
			
				
		} catch (FileNotFoundException a) {
			System.out.println("City File not found");
			System.exit(-1);
		}
		
		
		
		
		
		
	
		
		//Read the roads file and create new roads from the data
		try {
			
			Scanner roadsScanner = new Scanner(new FileInputStream(roadsFile));
			
			while (roadsScanner.hasNext()) {
				String line = roadsScanner.nextLine();
				String fields[] = line.split("(\s+)"); //Uses regular expressions to separate from_city, to_city, and dist.
				
				Road newRoad;
				
				
				if (fields[0] == "") { //Some of the data in the file has extra blank space 
					//at the beginning of each line, so the data stored needs to be 
					//modified accordingly
					int from_city = Integer.parseInt(fields[1].trim());
					int to_city = Integer.parseInt(fields[2].trim());
					int distance = Integer.parseInt(fields[3].trim());
					
					newRoad = new Road(from_city, to_city, distance);
					numOfEdges++;
					
					
					//Enter the road into the linked list
					if (adjList[from_city - 1] == null) {
						adjList[from_city - 1] = new LinkedList<Road>();
						adjList[from_city - 1].add(newRoad);
					
					} else {
						adjList[from_city - 1].add(newRoad);
					}
					
				
				} else {
					int from_city = Integer.parseInt(fields[0].trim());
					int to_city = Integer.parseInt(fields[1].trim());
					int distance = Integer.parseInt(fields[2].trim());
					
					newRoad = new Road(from_city, to_city, distance);
					numOfEdges++;
					
					
					
					//Enter the road into the linked list
					if (adjList[from_city - 1] == null) { //If there is no linked list for this specific from_city, created one.
						adjList[from_city - 1] = new LinkedList<Road>();
						adjList[from_city - 1].add(newRoad);
					
					} else { //If there is a linked list, then add this road to that linked list.
						adjList[from_city - 1].add(newRoad);
					}
				}
				
				
				
				
				
			}
			
		} catch (FileNotFoundException a) {
			System.out.println("Roads File not found");
			System.exit(-1);
			
		}
		
				
		
				
				
				
		
			
		
				
		
	
	

		
		
		
		
		
		
		
		
		


    //TODO: set up the query request with a while loop for the following functions:
    //   Q   Query the city information by entering the city code.
    //   F   Find the minimum distance between two cities.
    //   I   Insert a road by entering two city codes and distance.
    //   R   Remove an existing road by entering two city codes.
    //   D   Display this message.
    //   E   Exit.
    //Realize all the function in the above list.
		System.out.println("Command? ");
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			String query = input.nextLine().trim();
			
			//Exit the program (E)
			if (query.equals("E")) {
				break;
			}
			
			//Display message (D)
			if (query.equals("D")) {
				System.out.println("Q   Query the city information by entering the city code. " + "\n" + 
									" F   Find the minimum distance between two cities. " + "\n" + 
									"I     Insert a road by entering two city codes and distance. " + "\n" + 
									"R   Remove an existing road by entering two city codes." + "\n" + 
									"D   Display this message. " + "\n" + 
									"E   Exit");
			}
				
			
			//	Q   Query the city information by entering the city code.
			if (query.equals("Q")) {
				query(input, citiesList);
			}
			
				
			//F   Find the minimum distance between two cities. 
			if (query.equals("F")) {
			
				System.out.println("Please enter the city codes in all caps with one space in between the codes.");
				System.out.println("City codes: ");
				String cityCodes = input.nextLine().trim();
				String city1 = cityCodes.substring(0, 2);
				String city2 = cityCodes.substring(3);
				
				CitiesGraph findDistance = new CitiesGraph();
				findDistance.adjList = adjList;
				findDistance.citiesList = citiesList;
				findDistance.numOfCities = numOfCities;
				
				findDistance.minidistance(city1, city2);
			
			}
			
			//I     Insert a road by entering two city codes and distance.
			if (query.equals("I")) {
				System.out.println("Please enter the city codes in all caps with one space in between the codes, then the distance after another space");
				System.out.println("City codes and distance: ");
				String cityCodes = input.nextLine().trim();
				String city1 = cityCodes.substring(0, 2);
				String city2 = cityCodes.substring(3, 5);
				int distance = Integer.valueOf(cityCodes.substring(6));
				
				
				CitiesGraph insertRoad = new CitiesGraph();
				insertRoad.adjList = adjList;
				insertRoad.citiesList = citiesList;
				insertRoad.numOfCities = numOfCities;
				
				//Insert the road and update the adjacency list with the new road.
				adjList = insertRoad.insertRoad(city1, city2, distance);
				
				
			}
			
			//R   Remove an existing road by entering two city codes.
			if (query.equals("R")) {
				System.out.println("Please enter the city codes in all caps with one space in between the codes.");
				System.out.println("City codes: ");
				String cityCodes = input.nextLine().trim();
				String city1 = cityCodes.substring(0, 2);
				String city2 = cityCodes.substring(3);
				
				CitiesGraph roadDelete = new CitiesGraph();
				roadDelete.adjList = adjList;
				roadDelete.citiesList = citiesList;
				roadDelete.numOfCities = numOfCities;
				
				
				roadDelete.deleteRoad(city1, city2);
				
			}
			
			
			
		}
				
	}
	
	public static void query(Scanner input, LinkedList<City> citiesList) {
			System.out.println("Enter city code(s) in all caps separated by 1 space in between each code: ");
			String cityCode = input.nextLine().trim();
			String currentCode = "";
			int i = 1;
			while(i <= cityCode.length()) {
				
				if (cityCode.length() < 2) {
					System.out.println("Citycode too short!");
					
				} else if (cityCode.length() == 2) {
					currentCode = cityCode;
					
					//TEST
					//System.out.println(currentCode);
					
				} else {
					currentCode = cityCode.substring(i - 1, i+ 1);
					
							//TEST
							//System.out.println(currentCode);
				}
				
				
				
				for(City city: citiesList) {
					if (city.city_code.equals(currentCode)) {
						System.out.println(city.number + " " + city.city_code + " " + city.full_name + " " + city.population + " " + city.elevation);
					}
				}
				i += 3;
				
				
			}
			
	}

    //notes:
    //1. You need to find a way to maintain the cities (for command Q).
    //2. A digraph is needed for roads between cities. (For command F -- Dijkstra algorithm, I/R -- maintain adjacent array/list).
    //3. For command D or E, there is not too much things you need to do.

}
