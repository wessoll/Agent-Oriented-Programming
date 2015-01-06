package models;
import jade.core.Agent;

public abstract class BaseCar extends Agent implements interfaces.IVehicle{
	protected int speed;
	protected String identifier;
	protected TomTom navigation;
	
	public BaseCar(){
		navigation = new TomTom();
		System.out.println("TomTom aangemaakt");
	}
	
	public abstract void setup();

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
	
	
}