package models;
import jade.core.Agent;
import java.util.UUID;

public abstract class Car extends Agent implements interfaces.Vehicle {

	protected int speed;
	protected String identifier;
	protected TomTom navigation;
	
	public Car(int speed){
		this.speed = speed;
		this.identifier = UUID.randomUUID().toString();
		
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