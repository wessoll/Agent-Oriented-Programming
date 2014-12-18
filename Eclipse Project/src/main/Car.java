package main;
import jade.core.Agent;

public abstract class Car extends Agent implements interfaces.Vehicle{
	protected int speed;
	protected String identifier;
	protected Vertex starting_point;
	protected Vertex destination;
	
	public abstract void setup();
}