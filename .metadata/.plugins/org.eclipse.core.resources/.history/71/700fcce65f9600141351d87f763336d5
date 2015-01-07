package models;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;

/**
 * This is the "God" class which maintains the Road Network (all roads)
 * @author wesley
 *
 */
public class RoadNetwork extends Agent {
	private static final long serialVersionUID = 1381214470907052019L;

	private Vertex[] vertices;
	
	private int id;
	private boolean isClosed;
	
	public RoadNetwork() {
		constructRoadNetwork();
	}
	
	public void setup() {
		
	}
	
	/**
	 * Creates a new Road Network by linking vertices with each other
	 */
	public void constructRoadNetwork() {
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

	public Vertex getVertex(String name) {
		for(Vertex vertex : this.vertices) {
			if (vertex.getName().equals(name)) {
				return vertex;
			}
		}
		return null;
	}

	public Vertex[] getVertices() {
		return vertices;
	}
	
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
	
	public void recieveMessage(){
		ACLMessage msg = receive();
		if (msg != null){
			System.out.println("Herberekenen van route!");
			String message = msg.getContent();
			String[] infoList = message.split(", ");
			this.id = Integer.parseInt(infoList[0]);
			this.isClosed = Boolean.parseBoolean(infoList[1]);
		}
	}
}
