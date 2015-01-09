package models;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.CyclicBehaviour;

/**
 * This Agent holds the most recent map and sends it on request
 * @author wesley
 *
 */
public class RoadNetwork extends Agent {
	private static final long serialVersionUID = 1381214470907052019L;

	private Vertex[] roadMap;
	
	public RoadNetwork() {
		constructRoadNetwork();
	}
	
	public void setup() {
		// Add a behavior which handles received messages
		addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				try {
					ACLMessage message = receive();
	                if (message != null) {
	                	System.out.println("Received new Message");
	                	
	                	RoadNetwork thisRoadNetwork = (RoadNetwork)this.myAgent;
	                	
	                	/**
	                	 * Road Closed/Open Message
	                	 */
	                	if (message.getOntology().equals("ClosedRoad")) {
	                		System.out.println("New Closed Road message");
	                		
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
	                		System.out.println("New Request for RoadMap message");
	                		
	                		// return the Road Map
	                		ACLMessage replyMessage = message.createReply();
	                		replyMessage.setPerformative(ACLMessage.INFORM);
	                		replyMessage.setOntology("RoadMap");
	                		replyMessage.setContentObject(thisRoadNetwork.getRoadMap());
	                		send(replyMessage);
	                		
	                		System.out.println("Returning RoadMap");
	                	}
	                	
	                	block();
	                }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	/**
	 * Creates a new Road Network by linking vertices with each other
	 */
	private void constructRoadNetwork() {
		Vertex denHelder = new Vertex("Den Helder");
		Vertex groningen = new Vertex("Groningen");
		Vertex amsterdam = new Vertex("Amsterdam");
		Vertex utrecht = new Vertex("Utrecht");
		Vertex denHaag = new Vertex("Den Haag");
		
		amsterdam.setAdjacencies(new Edge[] {
				new Edge(denHelder, 90, 1, 40),
				new Edge(groningen, 200, 2, 60),
				new Edge(utrecht, 40, 3, 20),
				new Edge(denHaag, 65, 4, 20)
		});
		groningen.setAdjacencies(new Edge[] {
				new Edge(denHelder, 155, 5, 100),
				new Edge(amsterdam, 200, 6, 60),
				new Edge(utrecht, 195, 7, 40)
		});
		utrecht.setAdjacencies(new Edge[] {
				new Edge(denHaag, 65, 8, 20),
				new Edge(amsterdam, 40, 9, 20),
				new Edge(groningen, 195, 10, 40)
		});		
		denHaag.setAdjacencies(new Edge[] {
				new Edge(amsterdam, 65, 11, 20),
				new Edge(utrecht, 65, 12, 20)
		});
		denHelder.setAdjacencies(new Edge[] {
				new Edge(groningen, 155, 13, 100),
				new Edge(amsterdam, 90, 14, 40)
		});
		
		this.roadMap = new Vertex[]{denHelder, groningen, amsterdam, utrecht, denHaag};
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

	public Vertex[] getRoadMap() {
		return roadMap;
	}
	
	
	
}
