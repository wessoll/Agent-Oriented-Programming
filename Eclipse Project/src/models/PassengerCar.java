package models;


import java.util.UUID;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentController;
import behaviours.DriveBehavior;

public class PassengerCar extends BaseCar {
	private static final long serialVersionUID = 8962335670763104350L;
	
	public PassengerCar(){
		super();
	}
	
	public void setup() {
		super.setup();
		
		// Add a behavior that plots a route once we have the RoadMap received @todo ofcourse this should be done in a better way
		addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				PassengerCar thisCar = (PassengerCar)this.myAgent;
				
				if (thisCar.getNavigation().getRoadMap() != null) {
					// We can begin driving
					thisCar.plotRouteAndStartEngines();

					this.myAgent.removeBehaviour(this);
				}
			}
		});
	}
	
	// Utility method which plot's a route and starts the engines by following that route
	private void plotRouteAndStartEngines() {
		getNavigation().plotRoute(
				getNavigation().getVertex("Den Haag"), 
				getNavigation().getVertex("Groningen"));
		
		System.out.println(this.getName() + " - " + getNavigation().getCurrentRoute());
		
		
		
		
	}
}
