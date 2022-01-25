/*THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS 
 * OR COPIED FROM ONLINE RESOURCES. Tommy Skodje*/

package cs171a8test;

public class Road implements Comparable<Road>{
    int from_city;
    int to_city;
    int distance;
    
    public Road(int from_city, int to_city, int distance) { //Road Constructor
    	this.from_city = from_city;
    	this.to_city = to_city;
    	this.distance = distance;
    }
    
    public int compareTo(Road a) {
    	return this.distance - a.distance;
    }
    
}
