package cs211;

import java.util.*;

public class Route {
	
	private School[] schools;
	private double distance;
	
	public Route(School[] schools) {
		
		this.schools = schools;
		
	}
	
	public School getSchool(int index) {
		return schools[index];
	}
	
	public double getRouteLength() {
		
		/*if (distance != 0) {
			return distance;
		}*/
		
		double totalDistance = 0.0;
		
		for (int i = 0; i < schools.length-1; i++) {
			
			totalDistance += Calculate.getDistance(schools[i], schools[i+1]);
			
		}
		
		distance = totalDistance;
		return totalDistance;
		
	}
	
	public Route duplicate() {
		
		return new Route(schools);
		
	}
	
	public int numSchools() {
		
		return schools.length;
		
	}
	
	public School[] getSchools() {
		
		return this.schools;
		
	}
	
	public void swap(int index1, int index2) {
		
		School temp;
		
		temp = schools[index1];
		schools[index1] = schools[index2];
		schools[index2] = temp;
		
		
	}
	
	public String toString() {
		
		String output = "";
		
		for (int i = 0; i < schools.length; i++ ) {
			
			if (i < schools.length-1) {
				output = output + schools[i].getName() + ",";
			}
			else {
				output = output + schools[i].getName();
			}
			
		}
		
		return output;
		
	}

}