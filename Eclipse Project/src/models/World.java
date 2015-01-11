package models;

/**
 * This class simulates our world. Car's drive on this. But the World isn't responsible for showing the latest information
 * about roads!
 * 
 * @author wesley
 *
 */
public class World {

	private static World instance = null;
	
	private Vertex[] world;
	
	public static World getInstance() {
	      if(instance == null) {
	         instance = new World();
	      }
	      return instance;
	   }
	
	protected World() {
		constructRoadNetwork();
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
		Vertex duitsland = new Vertex("Duitsland");
		
		amsterdam.setAdjacencies(new Edge[] {
				new Edge(denHelder, 90, 1),
				new Edge(groningen, 200, 2),
				new Edge(utrecht, 40, 3),
				new Edge(denHaag, 65, 4)
		});
		groningen.setAdjacencies(new Edge[] {
				new Edge(denHelder, 155, 5),
				new Edge(amsterdam, 200, 6),
				new Edge(utrecht, 195, 7),
				new Edge(duitsland, 500, 20)
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
		duitsland.setAdjacencies(new Edge[] {
				new Edge(groningen, 500, 21),
		});
		
		this.world = new Vertex[]{denHelder, groningen, amsterdam, utrecht, denHaag, duitsland};
	}

	public Vertex[] getWorld() {
		return world;
	}

	public void setWorld(Vertex[] world) {
		this.world = world;
	}
	
	/**
	 * Returns the Edge connected to the Vertex
	 * @param vertexName				Name of the Vertex where the Edge is connected to
	 * @param id						The ID of the Edge
	 * @return Edge
	 */
	public Edge getEdgeById(int id) {
		// @todo performance optimize!
		for (Vertex vertex : this.world) {
			for(Edge edge : vertex.getAdjacencies()) {
				if (edge.getId() == id) { 
					return edge;
				}
			}
		}
		return null;
	}
}
