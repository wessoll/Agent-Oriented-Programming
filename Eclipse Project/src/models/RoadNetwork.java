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

	private Vertex[] vertices;
	
	public RoadNetwork() {
		constructRoadNetwork();
	}
	
	public void setup() {
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
				new Edge(denHelder, 90, 1),
				new Edge(groningen, 200, 2),
				new Edge(utrecht, 40, 3),
				new Edge(denHaag, 65, 4)
		});
		groningen.setAdjacencies(new Edge[] {
				new Edge(denHelder, 155, 5),
				new Edge(amsterdam, 200, 6),
				new Edge(utrecht, 195, 7)
		});
		utrecht.setAdjacencies(new Edge[] {
				new Edge(denHaag, 65, 8),
				new Edge(amsterdam, 40, 9),
				new Edge(groningen, 195, 10)
		});		
		denHaag.setAdjacencies(new Edge[] {
				new Edge(amsterdam, 65, 11),
				new Edge(utrecht, 65, 12)
		});
		denHelder.setAdjacencies(new Edge[] {
				new Edge(groningen, 155, 13),
				new Edge(amsterdam, 90, 14)
		});
		
		this.vertices = new Vertex[]{denHelder, groningen, amsterdam, utrecht, denHaag};
	}

	/**
	 * Returns a Vertex by it's name
	 * @param name									Name of the Vertex
	 * @return
	 */
	public Vertex getVertex(String name) {
		for(Vertex vertex : this.vertices) {
			if (vertex.getName().equals(name)) {
				return vertex;
			}
		}
		return null;
	}
	
	/**
	 * Updates the RoadNetwork with a closed or open edge
	 * @param edgeId								ID of the Edge to update
	 * @param isClosed								Whether or not the Edge is open/closed
	 */
	private void updateRoadNetwork(int edgeId, boolean isClosed) {
		// Find the Edge corresponding with the edgeId
		for(Vertex vertice : this.vertices) {
			for(Edge edge : vertice.getAdjacencies()) {
				if (edge.getId() == edgeId) {
					edge.setClosed(isClosed);
					break;
				}
			}
		}
	}
	
}
