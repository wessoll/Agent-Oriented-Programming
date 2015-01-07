package models;
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
	private List<Vertex> currentRoute;
	private Edge currentEdge;
	
	private static RoadNetwork roadnetwork;
	
	public TomTom(){
		currentRoute = new ArrayList<Vertex>();
		currentEdge = null;
		
		this.roadnetwork = RoadNetwork.getInstance();
	}
	
	public void plotRoute(Vertex source, Vertex destination) {
		this.computePaths(source);
		this.currentRoute = this.getShortestPathTo(destination);
	}
	
	public void printCurrentRoute() {
		System.out.println("Current Route: " + currentRoute);
	}
	
	/**
	 * Utility Methods
	 */
	
	/**
	 * Should be called before getShortestPathTo(destination)
	 * @param source					Where you want to go from
	 */
	private void computePaths(Vertex source) {
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
	private List<Vertex> getShortestPathTo(Vertex destination) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = destination; vertex != null; vertex = vertex.getPrevious()) {
			path.add(vertex);
		}
		Collections.reverse(path);
		
		this.currentRoute = path;
		
		return path;
	}
	
	/**
	 * Getters/Setters
	 */

	public List<Vertex> getCurrentRoute() {
		return currentRoute;
	}

	public void setCurrentRoute(List<Vertex> currentRoute) {
		this.currentRoute = currentRoute;
	}

	public static RoadNetwork getRoadnetwork() {
		return roadnetwork;
	}

	public static void setRoadnetwork(RoadNetwork roadnetwork) {
		TomTom.roadnetwork = roadnetwork;
	}

	public Edge getCurrentEdge() {
		return currentEdge;
	}

	public void setCurrentEdge(Edge currentEdge) {
		this.currentEdge = currentEdge;
	}
	
	
}
