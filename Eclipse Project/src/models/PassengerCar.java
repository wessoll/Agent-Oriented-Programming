package models;

public class PassengerCar extends Car {

	public PassengerCar(){
		super();
		
		// For test purposes we want to take a travel from Den Haag to Groningen
		getNavigation().plotRoute(
				RoadNetwork.getInstance().getVertex("Den Haag"), 
				RoadNetwork.getInstance().getVertex("Groningen"));
		
		// The route is saved in the navigation and we can print it
		getNavigation().printCurrentRoute();
	}
	
	public void setup(){
		Object[] args = getArguments();
		
		// Add a behavior which is responsible for the car movement (driving)
		addBehaviour(new DriveBehavior(this) {
		      protected void handleElapsedTimeout() {
		    	  // nop
		      }});
	}
}
