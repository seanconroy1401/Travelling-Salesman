package cs211;

public class School {
	
	private String name;
	private double latitude;
	private double longitude;
	
	public School(String name, double latitude, double longitude) {
		
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getLat() {
		return this.latitude;
	}
	
	public double getLon() {
		return this.longitude;
	}
	
	public String toString() {
		
		String output = this.name + " Latitude: " + this.latitude + " Longitude: " + this.longitude;
		return output;
		
	}

}