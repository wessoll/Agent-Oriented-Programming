package models;


import interfaces.iVehicle;

import java.util.Queue;
import java.util.UUID;

import behaviours.DriveBehavior;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentController;

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
					try {
						thisCar.plotRouteAndStartEngines();
					} catch (Exception e) {
						e.printStackTrace();
					}
					this.myAgent.removeBehaviour(this);
				}
			}
		});
	}
	
	// Utility method which plot's a route and starts the engines by following that route
	private void plotRouteAndStartEngines() throws Exception {
		getNavigation().plotRoute(
				getNavigation().getVertex("Den Haag"), 
				getNavigation().getVertex("Groningen"));
		
		System.out.println("Route Plotted: " + getNavigation().getCurrentRoute());
		
		if (getNavigation().getCurrentRoute().size() < 2) {
			throw new Exception("Route too small");
		}
		
		// Place the car on the road
		Vertex startVertex = getNavigation().getCurrentRoute().get(0);
		Vertex nextVertex = getNavigation().getCurrentRoute().get(1);
		Edge startEdge;
		
		boolean didMove = false;
		for (Edge edge : startVertex.getAdjacencies()) {
			if (edge.getDestination().equals(nextVertex)) {
				// See if there is any room on this Edge
				if (!edge.isClosed()) {
					for (Queue<iVehicle> lane : edge.getLanes()) {
						try {
							lane.add(this);
							setCurrentEdge(edge);
							
							// Update TomTom
							getNavigation().getCurrentRoute().remove(0);
							didMove = true;
							break;
						} catch (IllegalStateException e) { // There is no space!
							continue; // Check next lane
						}
					}
				}
				break;
			}
		}
		if (!didMove) {
			throw new Exception("Car couldn't start.");
		}
		System.out.println("New position: " + getNavigation().getCurrentRoute());
		
		// Now start our engines!
		addBehaviour(new DriveBehavior(this));
		
		
		
	}
}
