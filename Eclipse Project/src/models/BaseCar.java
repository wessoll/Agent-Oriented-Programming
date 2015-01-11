package models;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.util.UUID;

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
                	block();
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