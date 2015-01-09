package models;

import interfaces.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This class represents a line between two Vertices
 * @author wesley
 *
 */
public class Edge implements Serializable {
	private boolean hasObstacle = false;
	//private static List<Vehicle> cars; // The Cars that are on this lane (the same for every car)
	private boolean isClosed = false;
	private int speedLimit = 120;
	private int id;
	private boolean closed;
	
	private static Queue lane; // The Cars that are on this lane (the same for every car)
	
	public final Vertex destination;
	public double weight;
	
	/**
	 * Constructor
	 * @param destination				Where you want to go to
	 * @param weight					The weight for this path
	 */
	public Edge(Vertex destination, double weight, int id, int capacity ) {
		Comparator<Vehicle> comparator = new PriorityComparator();
		Edge.lane = new PriorityQueue<Vehicle>(capacity, comparator); 
		this.id = id;
		this.destination = destination;
		this.weight = weight;
	}

	public Queue<Vehicle> getCars() {
		return lane;
	}

	public void setCars(Queue<Vehicle> cars) {
		Edge.lane = cars;
	}

	public void addCar(Vehicle car) {
		Edge.lane.add(car);
	}
	
	public void removeCar(Vehicle car) {
		Edge.lane.remove(car);
	}
	
	public int laneSize(){
		return lane.size();
	}
	
	public boolean isHasObstacle() {
		return isClosed;
	}

	public void setHasObstacle(boolean hasObstacle) {
		this.isClosed = hasObstacle;
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
	
	
}


