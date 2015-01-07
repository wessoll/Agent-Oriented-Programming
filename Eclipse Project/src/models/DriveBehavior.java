package models;

import interfaces.Vehicle;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

/**
 * The behavior that moves the car forward, it is a cycle behavior and is called every x interval
 * @author wesley
 *
 */
public class DriveBehavior extends Behaviour {

	private static final long serialVersionUID = 4555157319361129456L;

	public DriveBehavior(Agent agent) {
		super(agent);
	}
	
	/**
	 * What to do in this action:
	 * 
	 * Moves the car one position forward
	 */
	@Override
	public void action() {
		// @todo move the car forward one place
		Vehicle car = (Vehicle)this.myAgent; // Parse the Agent to Vehicle so we can use the Vehicle operations

		// Determine which edge to drive on
		Vertex nextVertex = (car.getNavigation().getCurrentRoute().size() > 1) ? car.getNavigation().getCurrentRoute().get(1) : null; // Get next Vertex (if any)
		Vertex currentVertex = car.getNavigation().getCurrentRoute().get(0);
		
		if (nextVertex != null) {
			System.out.println("moving to next vertex: " + nextVertex.getName());
			for (Edge edge : currentVertex.getAdjacencies()) { // For every Edge
				if (edge.getDestination().equals(nextVertex)) {
					// We found our next edge: move one forward
					if (car.getNavigation().getCurrentEdge() != null) {
						car.getNavigation().getCurrentEdge().removeCar(car); // Remove from old location
					}
					// Set it's new location (both on the Edge as well as the car)
					edge.addCar(car);
					car.getNavigation().setCurrentEdge(edge);
					
					System.out.println("There are " + edge.getCars().size() + "cars on the edge starting from " + currentVertex.getName());
					
					break;
				}
			}
		} else {
			// Car reached it's destination. Remove it from it's location
			car.getNavigation().getCurrentEdge().removeCar(car);
			car.getNavigation().setCurrentEdge(null);
		}
		
		car.getNavigation().getCurrentRoute().remove(0); // Remove first object
	}

	/**
	 * Checks whether we are done or not
	 */
	@Override
	public boolean done() {
		BaseCar car = (BaseCar)this.myAgent;
		
		return car.getNavigation().getCurrentRoute().isEmpty(); // if Car has no route anymore (i.e. reached it's destination)	
	}	
}
