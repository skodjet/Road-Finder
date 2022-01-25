/*THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS 
 * OR COPIED FROM ONLINE RESOURCES. Tommy Skodje*/

package cs171a8test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CitiesGraph extends Test {
    //TODO: maintain the graph (I/R) for roads between citites here and realize the command: F
    //you can add data fields here. 
	City startingCity;
	City destinationCity;
	int currentCityNumber;
	int destinationCityNumber;
	int distance = 0;
	public LinkedList<Road>[] adjList;
	public LinkedList<City> citiesList;
	public int numOfCities;

    public LinkedList<Road>[] insertRoad(String city1, String city2, int distance){
        //TODO
    	
    	
    	for(City city: citiesList) {
			if (city.city_code.equals(city1)) {
				
				this.currentCityNumber = city.number;
				startingCity = city;
			}
			
			 if (city.city_code.equals(city2)) {
				
				this.destinationCityNumber = city.number;
				
				destinationCity = city;
			}
		}
    	
    	
    	
    	//If a road already exists, print a warning message
    	for(Road road: adjList[currentCityNumber - 1]) {
    		if (road.from_city == currentCityNumber && road.to_city == destinationCityNumber) {
    			System.out.println("Warning, road already exists.");
    		}
    	}
    	
    	Road roadToAdd = new Road(currentCityNumber, destinationCityNumber, distance);
    	
    	 this.adjList[currentCityNumber - 1].add(roadToAdd);
    	 
    	 System.out.println("You have inserted a road from " + startingCity.full_name + " to " + destinationCity.full_name + " with a distance of " + distance);
    	 
    	 return this.adjList;
    	 
    	
    }

    
    
    
    public LinkedList<Road>[] deleteRoad(String city1, String city2){
        //TODO
    	boolean roadExists = false;
    	
    	for(City city: citiesList) {
			if (city.city_code.equals(city1)) {
				this.currentCityNumber = city.number;
				startingCity = city;
			}
			
			if (city.city_code.equals(city2)) {
				
				this.destinationCityNumber = city.number;
				
				destinationCity = city;
			}
			
    	}
    	
    	
    	
    	for(Road road: adjList[this.currentCityNumber - 1]) {
    		if (road.from_city == this.currentCityNumber && road.to_city == this.destinationCityNumber) {
    			System.out.println("The road from " + startingCity.full_name + " to " + destinationCity.full_name + " has been removed.");
    			adjList[this.currentCityNumber - 1].remove(road);
    			roadExists = true;
    			break;
    		}
    	}
    		
    	if (!roadExists) {
    		System.out.println("The road from " + startingCity.full_name + " to " + destinationCity.full_name + " doesn't exist.");
    	}
    		
    		
    		return this.adjList;
    	
    	
    	
    }

    public int minidistance(String city1, String city2)
    {
        //TODO
        //You also need to provide the route details.
    	
				

			
			for(City city: citiesList) {
				if (city.city_code.equals(city1)) {
					
					this.currentCityNumber = city.number;
					startingCity = city;;
				}
				
				 if (city.city_code.equals(city2)) {
					
					this.destinationCityNumber = city.number;
					
					destinationCity = city;
				}
			}
			
			//Dijkstra's Algorithm
			boolean[] visited = new boolean[numOfCities];
			int[] distTo = new int[numOfCities];
			
			int[] edgeTo = new int[numOfCities];
			
			PriorityQueue<Road> roadsToVisit = new PriorityQueue<Road>();
			
			
			LinkedList<Integer> minPath = new LinkedList<>();
			
			
			//Initialize all distances except the starting node to the max integer value
			for(int i = 0; i < distTo.length; i++) {
				if (i != startingCity.number - 1) {
					distTo[i] = Integer.MAX_VALUE;
				}
			}
			
			//Enqueue the starting city into the pq
			for(int i = 0; i < adjList[currentCityNumber - 1].size(); i++) {
				Road roadToAdd = adjList[currentCityNumber - 1].get(i);
				roadsToVisit.add(roadToAdd);
			}
			
			
			visited[startingCity.number - 1] = true;
		
			
			//Print statement for visited[]
			/*
			for(int i = 0; i < visited.length; i++) {
				System.out.print(visited[i] + ", ");
			}
			*/
			
			//Print statement for pq roadsToVisit
			/*
			while(roadsToVisit.size() > 0) {
			System.out.println(roadsToVisit.remove().distance);
			}
			*/
			
			//Print statement for edgeTo[]
			/*
			for(int i = 0; i < edgeTo.length; i++) {
				System.out.print(edgeTo[i] + ", ");
			}
			*/
			
			//Print statement for distTo[]
			/*
			for(int i = 0; i < distTo.length; i++) {
				System.out.print(distTo[i] + ", ");
			}
			*/
			
			/*
			
			for(int i = 0; i < visited.length; i++) { //If all nodes are visited, return distance.
				if (visited[i] == true) {
					break;
				}
				return distance;
			}
			*/
			
			boolean finished = false;
			
			
			for(int x = 0; x < numOfCities * 2; x++) {
				
				finished = true;
				
				
				while(roadsToVisit.size() > 0) {
					
					Road currentRoad = roadsToVisit.remove();
					visited[currentRoad.from_city - 1] = true;
					
					//If the distance to the to_city of the current road is less than the from_city's distance, relax the edge.
					if (distTo[currentRoad.from_city - 1] + currentRoad.distance < distTo[currentRoad.to_city - 1]) {
						distTo[currentRoad.to_city - 1] = distTo[currentRoad.from_city - 1] + currentRoad.distance;
						
						//update edgeTo for the to_city if the edge is relaxed.
						edgeTo[currentRoad.to_city - 1] = currentRoad.from_city;
					}
				}
				
				
				
				//Enqueue the node with the smallest distTo[] value that has not been visited.
				int leastDistance = Integer.MAX_VALUE;
				int indexOfLeastDistance = Integer.MAX_VALUE;
				
				int j;
				for(j = 0; j < distTo.length; j++) {
					if (distTo[j] < leastDistance && visited[j] == false && adjList[j] != null) {
						leastDistance = distTo[j];
						indexOfLeastDistance = j;
					}
				}
				
				
				if (leastDistance == Integer.MAX_VALUE) { //If there are no more nodes that are unvisited, return the distance and the path
					
					int currentNode = destinationCityNumber;
					int previousNode = edgeTo[destinationCityNumber - 1];
					
					
					while (previousNode != startingCity.number) {
						minPath.add(previousNode);
						previousNode = edgeTo[previousNode - 1];
					}
					
					String path = startingCity.city_code + ", ";
					
					
					while(minPath.size() > 0) {
						int nextCityCode = minPath.removeLast();
						
						for(City city: citiesList) {
							if (city.number == nextCityCode) {
								path = path + city.city_code + ", ";
							}
						
						}
						
					}
					
					path = path + destinationCity.city_code;
					
					System.out.println("The minimum distance between " + startingCity.full_name + " and " + destinationCity.full_name + " is " + distTo[destinationCityNumber - 1]);
					System.out.print(" through the route: " + path);
					return distTo[destinationCityNumber - 1];
				}
				
				for(int i = 0; i < adjList[indexOfLeastDistance].size(); i++) {
					Road roadToAdd = adjList[indexOfLeastDistance].get(i);
					roadsToVisit.add(roadToAdd);
				}
				
				
				
				
				/*
				for(int i = 0; i < visited.length - 1; i++) { //If all nodes are visited, return distance.
					if (visited[i] == false) {
						System.out.println("not finished yet!");
						finished = false;
					}
				}
				*/
				/*
				if (finished) {
					this.distance = distTo[this.destinationCityNumber - 1];
					break;
				}
				*/
				
			}
			
			System.out.println("The road from " + startingCity.full_name + " to " + destinationCity.full_name + " doesn't exist.");
			return 0;
			
			
	}
}
