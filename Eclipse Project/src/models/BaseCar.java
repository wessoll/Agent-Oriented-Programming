package models;
import interfaces.iVehicle;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.util.Queue;
import java.util.UUID;

import behaviours.DriveBehavior;

public abstract class BaseCar extends Agent implements interfaces.iVehicle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8387423214541738456L;
	protected int speed;
	protected String identifier;
	protected TomTom navigation;
	private int priority = 1;
	
	private Edge currentEdge = null;
	private Edge previousEdge = null;
	
	public BaseCar() {
		this.speed = 120;
		this.identifier = UUID.randomUUID().toString();
		
		navigation = new TomTom();
	}
	
	public void setup() {
		// Add a behavior which handles received messages
		addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				ACLMessage message = receive();
                if (message != null) {
                    
                    BaseCar thisCar = (BaseCar)this.myAgent;
                    
                    if (message.getOntology().equals("RoadMap")) { // A RoadMap has been received
                    	  try {
                    		  Vertex[] roadMap = (Vertex[])message.getContentObject();
                    		  thisCar.getNavigation().setRoadMap(roadMap);
                    		  
						} catch (UnreadableException e) {
							e.printStackTrace();
						}
                    }
                	//block();
                }
			}
		});	
		
		// This is executed only once to receive the latest roadmap
		// @todo the passenger car receives the roadmap and set's it in the tomtom. But all of this should actually
		// be done by the TomTom itself
		addBehaviour(new OneShotBehaviour(this) {
			public void action() {
				ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
				
				message.setOntology("RoadMap");
				message.addReceiver(new AID("InfoAgent", AID.ISLOCALNAME));
			    
			    send(message);
			}
		});
		
		// Add a behavior that plots a route once we have the RoadMap received @todo ofcourse this should be done in a better way
		addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				PassengerCar thisCar = (PassengerCar)this.myAgent;
					
				if (thisCar.getNavigation().getRoadMap() != null) {
					System.out.println(this.myAgent.getName() + "start driving!");
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
	protected void plotRouteAndStartEngines() throws Exception {
			getNavigation().plotRoute(
					getNavigation().getVertex("Den Haag"), 
					getNavigation().getVertex("Duitsland"));
			
			System.out.println(this.getName() +  " - Route Plotted: " + getNavigation().getCurrentRoute());
			
			if (getNavigation().getCurrentRoute().size() < 2) {
				throw new Exception("Route too small");
			}
			
			// Place the car on the world
			Vertex startVertex = getNavigation().getCurrentRoute().get(0);
			Vertex nextVertex = getNavigation().getCurrentRoute().get(1);
			Edge startEdge;
			
			boolean didMove = false;
			for (Edge edge : startVertex.getAdjacencies()) {
				if (edge.getDestination().equals(nextVertex)) {
					// See if there is any room on this Edge
					if (!edge.isClosed()) {
						// The World Edge is the Edge where all cars drive on (same reference)
						Edge worldEdge = World.getInstance().getEdgeById(edge.getId()); 

						for (Queue<iVehicle> lane : worldEdge.getLanes()) {
							try {
								if (lane.size() >= Edge.laneLimit) {
									throw new IllegalStateException();
								}
								lane.add(this);
								
								setCurrentEdge(edge);
								
								// Update TomTom
								getNavigation().getCurrentRoute().remove(0);
								didMove = true;
								break;
							} catch (IllegalStateException e) { // There is no space!
								System.out.println(this.getName() + " - Lane is full");
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
			System.out.println(this.getName() + " - New route: " + getNavigation().getCurrentRoute());
			
			// Now start our engines!
			addBehaviour(new DriveBehavior(this));
		}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public TomTom getNavigation() {
		return navigation;
	}
	
	public void setNavigation(TomTom navigation) {
		this.navigation = navigation;
	}

	public int getPriority() {
		return priority;
	}

	public Edge getCurrentEdge() {
		return currentEdge;
	}

	public void setCurrentEdge(Edge currentEdge) {
		this.currentEdge = currentEdge;
	}

	public Edge getPreviousEdge() {
		return previousEdge;
	}

	public void setPreviousEdge(Edge previousEdge) {
		this.previousEdge = previousEdge;
	}
	
	
}