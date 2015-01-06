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
}