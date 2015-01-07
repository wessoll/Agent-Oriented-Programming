package models;

public class Ambulance extends Car{
	private static final long serialVersionUID = 5281194453837534916L;
	private boolean alarms_on;
	
	public Ambulance(Vertex starting_point, Vertex destination, int speed, int identifier){
		super(starting_point, destination, speed, "Ambulance_" + identifier);
	}
	
	public void setup(){
		//todo?
	}
}
