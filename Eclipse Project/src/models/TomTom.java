package models;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

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
	
	private List<Vertex> currentRoute; // Plotted route (null if none)
	private Vertex[] roadMap; // The roadmap

	public TomTom() {
		this.currentRoute = new ArrayList<Vertex>();
	}
	
	/**
	 * Creates a shortest path between your current position and destination
	 * @param source					Current position
	 * @param destination				Destination
	 */
	public void plotRoute(Vertex source, Vertex destination) {
		this.computePaths(source);
		this.currentRoute = this.getShortestPathTo(destination);
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
	 * Returns a Vertex by it's name
	 * @param name									Name of the Vertex
	 * @return
	 */
	public Vertex getVertex(String name) {
		for(Vertex vertex : this.roadMap) {
			if (vertex.getName().equals(name)) {
				return vertex;
			}
		}
		return null;
	}

	public Vertex[] getRoadMap() {
		return roadMap;
	}

	public void setRoadMap(Vertex[] roadMap) {
		this.roadMap = roadMap;
	}

	public List<Vertex> getCurrentRoute() {
		return currentRoute;
	}

	public void setCurrentRoute(List<Vertex> currentRoute) {
		this.currentRoute = currentRoute;
	}	
}

