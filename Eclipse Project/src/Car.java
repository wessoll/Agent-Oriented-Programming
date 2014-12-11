import jade.core.Agent;


public class Car extends Agent {
	private int speed;
	private char type;
	private String identifier;
	private Place starting_point;
	private Place destination;
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Place getDestination() {
		return destination;
	}

	public void setDestination(Place destination) {
		this.destination = destination;
	}

	public Place getStarting_point() {
		return starting_point;
	}

	public void setStarting_point(Place starting_point) {
		this.starting_point = starting_point;
	}
	
	protected void setup(){
		Object[] args = getArguments();
		identifier = 		(String) args[0];
		type =				(char)	 args[1];
		starting_point =	(Place)  args[2];
		destination = 		(Place)  args[3];
		
		
        System.out.println("Car "+ identifier +" initialized.");
    }
}