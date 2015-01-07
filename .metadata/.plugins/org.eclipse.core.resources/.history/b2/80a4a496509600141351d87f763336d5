package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class represents a line between two Vertices
 * @author wesley
 *
 */
public class Edge {

	private static List<BaseCar> cars; // The Cars that are on this lane (the same for every car)
	private boolean hasObstacle = false;
	private int speedLimit = 120;
	
	public final Vertex destination;
	public double weight;
	
	/**
	 * Constructor
	 * @param destination				Where you want to go to
	 * @param weight					The weight for this path
	 */
	public Edge(Vertex destination, double weight) {
		this.cars = new ArrayList<BaseCar>();
		
		this.destination = destination;
		this.weight = weight;
	}

	public List<BaseCar> getCars() {
		return cars;
	}

	public void setCars(List<BaseCar> cars) {
		Edge.cars = cars;
	}

	public void addCar(BaseCar car) {
		Edge.cars.add(car);
	}
	
	public void removeCar(BaseCar car) {
		Edge.cars.remove(car);
	}
	
	public boolean isHasObstacle() {
		return hasObstacle;
	}

	public void setHasObstacle(boolean hasObstacle) {
		this.hasObstacle = hasObstacle;
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
}
