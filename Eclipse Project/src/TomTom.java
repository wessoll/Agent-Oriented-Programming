import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This class is used for navigation purposes
 * @author wesley
 *
 */
public class TomTom {

	public TomTom() {
		
	}
	
	/**
	 * Should be called before getShortestPathTo(destination)
	 * @param source					Where you want to go from
	 */
	public void computePaths(Vertex source) {
		source.setMinDistance(0.);
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		
		vertexQueue.add(source);
		
		while (!vertexQueue.isEmpty()) {
			Vertex vertexFromQueue = vertexQueue.poll();
			
			for (Edge edge : vertexFromQueue.getAdjacencies()) { // Visit each edge
				Vertex destination = edge.getDestination();
				
				double weight = edge.getWeight();
				double distanceThroughVertexFromQueue = vertexFromQueue.getMinDistance() + weight;
				
				if (distanceThroughVertexFromQueue < destination.getMinDistance()) {
					vertexQueue.remove(destination);
					
					destination.setMinDistance(distanceThroughVertexFromQueue);;
					destination.setPrevious(vertexFromQueue);
					
					vertexQueue.add(destination);
				}
			}
		}
	}
	
	/**
	 * Retrieves the shortest path to @param destination. Please note that computePath(source) should be called first
	 * @param destination					Where you want to got to
	 * @return								Shortest route from your source to destination
	 */
	public List<Vertex> getShortestPathTo(Vertex destination) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = destination; vertex != null; vertex = vertex.getPrevious()) {
			path.add(vertex);
		}
		Collections.reverse(path);
		
		return path;
	}
}
