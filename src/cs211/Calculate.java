package cs211;

public class Calculate {
	
	public static double probability(double f1, double f2, double temp) {
		
        if (f2 < f1) return 1;
        return Math.exp((f1 - f2) / temp);
        
    }
	
	public static double getDistance(School s1, School s2) { 
		//this method was provided by Phil in a lecture and I used it in the 10th closest school lab
		//it is the haversine formula for calculating distance in km between two co-ordinates
		
		double lat1 = s1.getLat();
		double lon1 = s1.getLon();
		double lat2 = s2.getLat();
		double lon2 = s2.getLon();
		
		final int R = 6371; //Radius of the earth
		
		Double latDistance = toRad(lat2-lat1);
		Double lonDistance = toRad(lon2-lon1);
		
		Double a = Math.sin(latDistance/2) * Math.sin(latDistance/2) + 
		Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
		Math.sin(lonDistance/2) * Math.sin(lonDistance/2);
		
		Double c= 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		Double distance = R * c;
		return distance;
		
		
	}
	
	public static double toRad(double value) { //this method was provided by Phil in Monday's 2pm lecture
		
		return value * Math.PI / 180;
		
	}

}