package models;


import java.util.UUID;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentController;
import behaviours.DriveBehavior;

public class PassengerCar extends Car {
	private static final long serialVersionUID = 8962335670763104350L;
	
	public PassengerCar(){
		super();
		
//		// For test purposes we want to take a travel from Den Haag to Groningen
//		getNavigation().plotRoute(
//				getNavigation().getRoadnetwork().getVertex("Den Haag"), 
//				getNavigation().getRoadnetwork().getVertex("Groningen"));
//		
//		// The route is saved in the navigation and we can print it
//		getNavigation().printCurrentRoute();
	}
	
	public void setup() {
		super.setup();
		
//		// Add a behavior which is responsible for the car movement (driving)
//		addBehaviour(new DriveBehavior(this) {
//		      protected void handleElapsedTimeout() {
//		    	  // nop
//		      }
//		});
	}
}
