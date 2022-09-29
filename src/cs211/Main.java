package cs211;

import java.io.*;
import java.util.*;

public class Main {
	
	private static double temp = 1000;
    private static double coolingFactor = 0.995;

	public static void main(String [] args) {
		
		School [] schools = new School[122];
		//Double [][] adjacencyMatrix = new Double[121][121]; //this will be the matrix containing the distances between all the points
		
		//This code is to read in the text file containing all the co-ordinates and initialize them as schools
		try {
			
		      File myFile = new File("120schools.txt");
		      Scanner scan = new Scanner(myFile);
		      
		      int whileCounter = 0; //keep track of the current iteration of the while loop
		      
		      while (scan.hasNextLine()) { //while there's still lines in the file, scan them and parse them
		    	  
		        String s = scan.nextLine();
		        int comma1 = s.indexOf(",");
		        int comma2 = s.lastIndexOf(",");
		        
		        double lat = Double.parseDouble(s.substring(comma1+1, comma2));
		        double lon = Double.parseDouble(s.substring(comma2 + 1));
		        
		        //String lat = s.substring(comma1+1, comma2);
		        //String lon = s.substring(comma2 + 1);			This commented out code was for testing that the co-ords were being read in properly
		        //System.out.println("Lat = " + lat + " Long = " + lon);
		        
		        //String schoolName = "School " +  whileCounter;
		        schools[whileCounter] = new School(String.valueOf(whileCounter), lat, lon);
		        
		        whileCounter++;
		        
		      }
		      
		      schools[121] = new School("0",53.3842125238019,-6.60088967821164);
		      
		      scan.close();
		      
		    } catch (FileNotFoundException e) {
		    	
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		      
		    }
		
		/*for (int i = 0; i < schools.length; i++) {
			
			System.out.println(schools[i].toString());
			
		}*/
		
		/*for (int i = 0; i < 121; i++) { //fill the adjacency matrix
			
			for (int j = 0; j < 121;  j++) {
				
				//System.out.println(schools[i]); these 
				//System.out.println(schools[j]);
				Double d = getDistance(schools[i].getLat(), schools[i].getLon(),schools[j].getLat(), schools[j].getLon());
				//System.out.println(d);
				adjacencyMatrix[i][j] = d;
				
			}
			
		}
		
		/*for (int i = 0; i < 121; i++) { //this was to test that the adjacency matrix was filling properly
			
			for (int j = 0; j < 121;  j++) {
				
				System.out.print(adjacencyMatrix[i][j] + " ");
				
			}
			
			System.out.print("\n");
			
		}*/
		
		//System.out.println(getDistance(schools[0].getLat(), schools[0].getLon(), schools[0].getLat(), schools[0].getLon()));
		
		double shortestLength = 9337.449236111433;
		
		for (int i = 0; i < 100000000; i++) {
			
			Route current = new Route(schools);
			Route best = current.duplicate();
			
			for (double t = temp; t > 1; t *= coolingFactor) {
				
				Route newRoute = current.duplicate(); //in each iteration a new route is developed
				
				int index1 = (int) (newRoute.numSchools() * Math.random()); 
				int index2 = (int) (newRoute.numSchools() * Math.random());
				
				if ((index1 != 0 && index1 != 121)  && (index2 != 0 && index2 != 121)) { //I randomly swap two edges in the route, as long as
					newRoute.swap(index1, index2);										//the first and last edges aren't swapped
				}
				
				double currentLength = current.getRouteLength(); //I get the length of both routes
				double newRouteLength = newRoute.getRouteLength();
				
				if (Math.random() < Calculate.probability(currentLength, newRouteLength, t)) { // and use the probability method in the Calculate
					current = newRoute.duplicate();											  //class to decide if the route will get accepted or not
				}
				
				//at the beginning of the algorithm, the high temperature makes the acceptance probability higher
				//this makes the algorithm more likely to accept the new route as a solution
				//as the temperature decreases, the probability decreases with it
				
				//this means the algorithm initially jumps through many permutations of routes (including bad ones)
				//as this means its more likely that a better route will be reached
				
				if (current.getRouteLength() < best.getRouteLength()) {
					best = current.duplicate();
				}
				
			}
					
			if (best.getRouteLength() < shortestLength) {
				
				shortestLength = best.getRouteLength();
				System.out.println("Final route length: " + best.getRouteLength());
				System.out.println("Route: " + best.toString());
				
			}
			
			if (i == 99999999) {
				System.out.println("Finished!");
			}
			
			
		}
		
	}
	

}