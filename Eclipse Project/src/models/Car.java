package models;
import jade.core.Agent;

public abstract class Car extends Agent implements interfaces.Vehicle{
	protected int speed;
	protected String identifier;
	protected Vertex starting_point;
	protected Vertex destination;
	protected TomTom navigation;
	
	public Car(Vertex starting_point, Vertex destination, int speed, String identifier){
		this.starting_point = starting_point;
		this.destination = destination;
		this.speed = speed;
		this.identifier = identifier;
		navigation = new TomTom();
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public TomTom getNavigation() {
		return navigation;
	}
	
	public void setNavigation(TomTom navigation) {
		this.navigation = navigation;
	}
	
	public abstract void setup();
}