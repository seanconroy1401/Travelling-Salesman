# Travelling-Salesman

## Aim
The aim of this project was to find a unique algorithmic solution to solve the infamous Travelling Salesman problem, in which a salesman needs to visit every city in a list once in the shortest route possible

## Algorithm Used
The algorithm I used was simulated annealing. Annealing is a process used in smithing in which metal is repeatedly heated and cooled causing atoms to shift randomly and remove impurities from the metal.

Simulated annealing attempts to avoid the problem that often occurs with travelling salesman solutions where the solution gets trapped in a local minima without finding the optimal solution.

This is done by allowing the algorithm to re-try "worse" solutions before once again attempting to optimise.

The program implements a temperature and cooling factor akin to real annealing.
``` java
    private static double temp = 1000;
    private static double coolingFactor = 0.995;
```

The following code snippet highlights how the program repeatedly recreates new random routes and then attempts to optimise them.
``` java
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
```