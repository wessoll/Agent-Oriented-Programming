package models;


import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import behaviours.DriveBehavior;

public class PassengerCar extends Car{
	private static final long serialVersionUID = 8962335670763104350L;
	
	public PassengerCar(int speed){
		super(speed);
		
		// For test purposes we want to take a travel from Den Haag to Groningen
		getNavigation().plotRoute(
				getNavigation().getRoadnetwork().getVertex("Den Haag"), 
				getNavigation().getRoadnetwork().getVertex("Groningen"));
		
		// The route is saved in the navigation and we can print it
		getNavigation().printCurrentRoute();
	}
	
	public void setup(){
		Object[] args = getArguments();
		
		// Add a behavior which is responsible for the car movement (driving)
		addBehaviour(new DriveBehavior(this) {
		      protected void handleElapsedTimeout() {
		    	  // nop
		      }
		});
		
		// Add a behavior which handles received messages
		addBehaviour(new CyclicBehaviour(this) {
				public void action() {
					ACLMessage message = receive();
					if (message != null) {
						System.out.println("Received new Message");
		                block();
					}
				}
		});	
	}
}
