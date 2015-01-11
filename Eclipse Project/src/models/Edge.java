package models;

import interfaces.iVehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This class represents a line between two Vertices
 * @author wesley
 *
 */
public class Edge extends Observable implements Serializable {
	public final Vertex destination;
	
	/**
	 * Road Characteristics
	 */
	private int id;
	private boolean isClosed 		= false;
	private int speedLimit 			= 120;
	private double weight; // @todo, this is now the same for every car, but should be made unique
	private List<Queue<iVehicle>> lanes;
	
	/**
	 * Constructor
	 * @param destination				Where you want to go to
	 * @param weight					The weight for this path
	 */
	public Edge(Vertex destination, double weight, int id) {
		int lanes = 2; // @todo make this dynamic in future
		
		this.lanes = new ArrayList<Queue<iVehicle>>();
		for (int i=0;i<lanes;i++) { // Initialize the lanes
			Comparator<iVehicle> comparator = new PriorityComparator();
			
			Queue<iVehicle> test = new PriorityQueue<iVehicle>(10);
			this.lanes.add(new PriorityQueue<iVehicle>(10, comparator)); 
		}
		
		this.id = id;
		this.destination = destination;
		this.weight = weight;
	}


	public int getSpeedLimit() {
		return speedLimit;
	}

	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
	}

	public Vertex getDestination() {
		return destination;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getWeight() {
		return weight;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Queue<iVehicle>> getLanes() {
		return lanes;
	}

	public void setLanes(List<Queue<iVehicle>> lanes) {
		this.lanes = lanes;
	}
}