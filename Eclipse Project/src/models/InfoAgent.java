package models;

import java.io.IOException;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.CyclicBehaviour;

/**
 * This Agent holds a RoadMap with the latest information in it (e.g. closed road etc.) 
 * 
 * NOTE! the Vehicle's do not drive on the Road Map but on the world!
 * 
 * @author wesley
 *
 */
public class InfoAgent extends Agent {
	private static final long serialVersionUID = 1381214470907052019L;

	private Vertex[] roadMap;
	
	public InfoAgent() {
		this.roadMap = World.getInstance().getWorld();
	}
	
	public void setup() {
		// Add a behavior which handles received messages
		addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				try {
					ACLMessage message = receive();
	                if (message != null) {
	                	System.out.println(message.getSender().getName() + "Received Request");
	                	InfoAgent thisRoadNetwork = (InfoAgent)this.myAgent;
	                	
	                	/**
	                	 * Road Closed/Open Message
	                	 */
	                	if (message.getOntology().equals("ClosedRoad")) {
	                		
	                		String[] parameters = message.getContent().split(",");
	                		
	                		if (parameters.length != 2) {
	                			throw new Exception("Invalid parameter length");
	                		}
	                		
	                		int edgeId = Integer.parseInt(parameters[0]);
	                		boolean isClosed = (parameters[1].equals("true") ? true : false);
	                		
	                		// Update the RoadMap Template with this new data
	                		updateRoadNetwork(edgeId, isClosed);
	                	}   
	                	
	                	/**
	                	 * Request for RoadMap Message
	                	 */
	                	else if (message.getOntology().equals("RoadMap")) {
	                		// return the Road Map
	                		sendMessageForRoadMap(message);
	                	}
	                }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	/**
	 * Sends a Message with the RoadMap to the requester
	 * @param originalMessage						The Message with the original request
	 * @throws IOException
	 */
	private void sendMessageForRoadMap(ACLMessage originalMessage) throws IOException {
		ACLMessage replyMessage = originalMessage.createReply();
		replyMessage.setPerformative(ACLMessage.INFORM);
		replyMessage.setOntology("RoadMap");
		replyMessage.setContentObject(getRoadMap());
		send(replyMessage);
	}
	
	/**
	 * Updates the RoadNetwork with a closed or open edge
	 * @param edgeId								ID of the Edge to update
	 * @param isClosed								Whether or not the Edge is open/closed
	 */
	private void updateRoadNetwork(int edgeId, boolean isClosed) {
		// Find the Edge corresponding with the edgeId
		for(Vertex vertice : this.roadMap) {
			for(Edge edge : vertice.getAdjacencies()) {
				if (edge.getId() == edgeId) {
					edge.setClosed(isClosed);
					break;
				}
			}
		}
	}

	/**
	 * Getters/Setters
	 */
	
	public Vertex[] getRoadMap() {
		return roadMap;
	}
}
