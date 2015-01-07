package models;

import interfaces.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a line between two Vertices
 * @author wesley
 *
 */
public class Edge {
	private boolean hasObstacle = false;
	private static List<Vehicle> cars; // The Cars that are on this lane (the same for every car)
	private boolean isClosed = false;
	private int speedLimit = 120;
	private int id;
	private boolean closed;
	
	public final Vertex destination;
	public double weight;
	
	/**
	 * Constructor
	 * @param destination				Where you want to go to
	 * @param weight					The weight for this path
	 */
	public Edge(Vertex destination, double weight, int id) {
		this.cars = new ArrayList<Vehicle>();
		this.id = id;
		this.destination = destination;
		this.weight = weight;
	}

	public List<Vehicle> getCars() {
		return cars;
	}

	public void setCars(List<Vehicle> cars) {
		Edge.cars = cars;
	}

	public void addCar(Vehicle car) {
		Edge.cars.add(car);
	}
	
	public void removeCar(Vehicle car) {
		Edge.cars.remove(car);
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
