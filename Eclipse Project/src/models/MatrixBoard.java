package models;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class MatrixBoard extends Agent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2860962565709711278L;

	public MatrixBoard(){
		super();
		System.out.println("Matrix bord aangemaakt");
	}
	
	protected void setup() {
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
		
		// This is executed only once to demonstrate Send Message
		addBehaviour(new OneShotBehaviour(this) {
			public void action() {
				MatrixBoard matrixBoard = (MatrixBoard)this.myAgent;
				
				matrixBoard.updateRoad(9, true);
			}
		});	
	}
	
	/**
	 * 
	 * @param edge_id							The ID of the closed or Open Edge
	 * @param closed							Whether the road is closed or open
	 */
	public void updateRoad(int edge_id, boolean closed){
		sendMessage(edge_id, closed);
	}
	
	/**
	 * Send a message about a closed road
	 * @param edge_id							The ID of the closed or Open Edge
	 * @param closed							Closed or Open
	 */
	private void sendMessage(int edge_id, boolean closed){
		System.out.println("Sending out Message");
		
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	    msg.setContent(edge_id + "," + closed);
	    msg.setOntology("ClosedRoad");
	    
	    msg.addReceiver(new AID("InfoAgent", AID.ISLOCALNAME));
	    // @todo send message to nearby Vehicles
	    
	    send(msg);
	}
}
