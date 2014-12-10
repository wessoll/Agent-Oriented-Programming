/**
 * This is the "God" class which maintains the Road Network (all roads)
 * @author wesley
 *
 */
public class RoadNetwork {

	private static RoadNetwork instance = null;

	private Vertex[] vertices;
	
	protected RoadNetwork() {
		
	}
	
	public static RoadNetwork getInstance() {
		if (instance == null) {
			instance = new RoadNetwork();
		}
		return instance;
	}
	
	/**
	 * Creates a new Road Network by linking vertices with each other
	 */
	public void constructRoadNetwork() {
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");		
		Vertex c = new Vertex("C");
		Vertex d = new Vertex("D");
		Vertex e = new Vertex("E");
		Vertex f = new Vertex("F");
		
		a.setAdjacencies(new Edge[]{
				new Edge(b, 10)});
		b.setAdjacencies(new Edge[] {
				new Edge(c, 2),
				new Edge(d, 5)
		});
		c.setAdjacencies(new Edge[] {
				new Edge(f, 20)
		});
		d.setAdjacencies(new Edge[] {
				new Edge(e, 1),
				new Edge(f, 10)
		});
		e.setAdjacencies(new Edge[] {
				new Edge(f, 1)
		});	
		
		this.vertices = new Vertex[]{a,b,c,d,e,f};
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertex[] vertices) {
		this.vertices = vertices;
	}
	
	
}
