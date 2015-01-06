package models;
/**
 * This is the "God" class which maintains the Road Network (all roads)
 * @author wesley
 *
 */
public class RoadNetwork {
	private static RoadNetwork instance = null;
	private Vertex[] vertices;
	
	protected RoadNetwork() {}
	
	public static RoadNetwork getInstance() {
		if (instance == null) {
			instance = new RoadNetwork();
			instance.constructRoadNetwork();
		}
		return instance;
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
				new Edge(denHelder, 90),
				new Edge(groningen, 200),
				new Edge(utrecht, 40),
				new Edge(denHaag, 65)
		});
		groningen.setAdjacencies(new Edge[] {
				new Edge(denHelder, 155),
				new Edge(amsterdam, 200),
				new Edge(utrecht, 195)
		});
		utrecht.setAdjacencies(new Edge[] {
				new Edge(denHaag, 65),
				new Edge(amsterdam, 40),
				new Edge(groningen, 195)
		});		
		denHaag.setAdjacencies(new Edge[] {
				new Edge(amsterdam, 65),
				new Edge(utrecht, 65)
		});
		denHelder.setAdjacencies(new Edge[] {
				new Edge(groningen, 155),
				new Edge(amsterdam, 90)
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
	
	
	
}
