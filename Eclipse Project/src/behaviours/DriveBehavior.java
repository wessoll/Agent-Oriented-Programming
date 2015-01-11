package behaviours;

import java.util.Queue;

import models.BaseCar;
import models.Edge;
import models.Vertex;
import models.World;
import interfaces.iVehicle;
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
		iVehicle car = (iVehicle)this.myAgent;
		
		Vertex startVertex = car.getNavigation().getCurrentRoute().get(0);
		Vertex nextVertex = (car.getNavigation().getCurrentRoute().size() > 1) ? car.getNavigation().getCurrentRoute().get(1) : null;
		
		// The World Edge is the Edge where all cars drive on (same reference)
		Edge currentWorldEdge = World.getInstance().getEdgeById(car.getCurrentEdge().getId());
					
		if (nextVertex == null) { // We reached our destination
			// Check if we can leave the lane
			boolean didLeave = false;
			
			for (Queue<iVehicle> lane : currentWorldEdge.getLanes()) {
				if (lane.peek() != null && lane.peek().equals(car)) { // Yes we can move!
					// Remove our old location
					car.getNavigation().getCurrentRoute().remove(0);
					
					// Remove ourselves from the previous Edge/Lane
					if (car.getPreviousEdge() != null) {
						lane.poll();
					}
					car.setPreviousEdge(car.getCurrentEdge());
					car.setCurrentEdge(null);
					System.out.println(this.myAgent.getName() + " - Destination Reached!");
					
					return;
				}
			}
			System.out.println(this.myAgent.getName() + " - We are stuck!");
			return;
		}
		System.out.println(this.myAgent.getName() + " - about to move to " + nextVertex.getName());
		// 1. Check if we can leave the road
		boolean canLeave = false;
		for (Queue<iVehicle> lane : currentWorldEdge.getLanes()) {
			if (lane.peek() != null && lane.peek().equals(car)) { // Yes we can move!
				boolean didMove = false;
				// See if we can move forward
				for (Edge edge : startVertex.getAdjacencies()) { // Get Next Vertex
					if (edge.getDestination().equals(nextVertex)) {
						// See if there is any room on this Edge
						if (!edge.isClosed()) {
							Edge nextWorldEdge = World.getInstance().getEdgeById(edge.getId());
							for (Queue<iVehicle> futureLane : nextWorldEdge.getLanes()) {
								try {
									if (futureLane.size() >= Edge.laneLimit) {
										System.out.println(this.myAgent.getName() + "lane limit reached: " + futureLane.size());
									
										throw new IllegalStateException();
									}
									futureLane.add(car);
									// Update TomTom
									car.getNavigation().getCurrentRoute().remove(0);
									
									System.out.println(this.myAgent.getName() + "lane size: " + lane.size());
									lane.poll();
									System.out.println(this.myAgent.getName() +  "after size: " + lane.size());
									
									car.setPreviousEdge(car.getCurrentEdge());
									car.setCurrentEdge(edge);
									System.out.println(this.myAgent.getName() + " - New route: " + car.getNavigation().getCurrentRoute());
									didMove = true;
									break;
								} catch (IllegalStateException e) { // There is no space!
									System.out.println("Sorry, this lane is full!");
									continue; // Check next lane
								}
							}
						}
						break;
					}
				}
				break;
			} else {
				return; // No we cannot move!
			}
		}
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
