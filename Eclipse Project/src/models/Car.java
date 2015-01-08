package models;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.util.UUID;

public abstract class Car extends Agent implements interfaces.Vehicle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8387423214541738456L;
	protected int speed;
	protected String identifier;
	protected TomTom navigation;
	
	public Car() {
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
                    System.out.println("Received new Message");
                    
                    Car thisCar = (Car)this.myAgent;
                    
                    if (message.getOntology().equals("RoadMap")) {
                    	  try {
                    		  thisCar.getNavigation().setRoadMap((Vertex[])message.getContentObject());
                    		  
                    		  System.out.println("Received RoadMap");
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
			    System.out.println("Request for RoadMap has been made");
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
}