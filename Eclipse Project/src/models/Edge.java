package models;

import interfaces.iVehicle;

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
	public final Vertex destination;
	
	/**
	 * Road Characteristics
	 */
	private int id;
	private boolean isClosed 		= false;
	private int speedLimit 			= 120;
	private double weight; // @todo, this is now the same for every car, but should be made unique
	private Queue<iVehicle> lane;
	
	/**
	 * Constructor
	 * @param destination				Where you want to go to
	 * @param weight					The weight for this path
	 */
	public Edge(Vertex destination, double weight, int id, int capacity ) {
		Comparator<iVehicle> comparator = new PriorityComparator();
		this.lane = new PriorityQueue<iVehicle>(capacity, comparator); 
		
		this.id = id;
		this.destination = destination;
		this.weight = weight;
	}

	public Queue<iVehicle> getCars() {
		return lane;
	}

	public void setCars(Queue<iVehicle> cars) {
		this.lane = cars;
	}

	public void addCar(iVehicle car) {
		this.lane.add(car);
	}
	
	public void removeCar(iVehicle car) {
		this.lane.remove(car);
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