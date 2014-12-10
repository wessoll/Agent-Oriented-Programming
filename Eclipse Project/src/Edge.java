
/**
 * This class represents a line between two Vertices
 * @author wesley
 *
 */
public class Edge {

	public final Vertex destination;
	public final double weight; //@todo, ofcourse this should be calculated individually for each car
	
	/**
	 * Constructor
	 * @param destination				Where you want to go to
	 * @param weight					The weight for this path
	 */
	public Edge(Vertex destination, double weight) {
		this.destination = destination;
		this.weight = weight;
	}

	public Vertex getDestination() {
		return destination;
	}

	public double getWeight() {
		return weight;
	}
	
	
}
