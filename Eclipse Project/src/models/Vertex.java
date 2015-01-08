package models;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * This class represents an "intersection"
 * @author wesley
 *
 */
public class Vertex implements Comparable<Vertex>, Serializable {

	private String name;
	private Edge[] adjacencies = new Edge[]{};
	private Vertex previous;
	private double minDistance = Double.POSITIVE_INFINITY;;
	
	public Vertex(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public int compareTo(Vertex o) {
		return Double.compare(this.minDistance, o.getMinDistance());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Edge[] getAdjacencies() {
		return adjacencies;
	}

	public void setAdjacencies(Edge[] adjacencies) {
		this.adjacencies = adjacencies;
	}

	public Vertex getPrevious() {
		return previous;
	}

	public void setPrevious(Vertex previous) {
		this.previous = previous;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}
	
	
}
