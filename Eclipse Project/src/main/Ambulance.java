package main;

public class Ambulance extends Car{
	private boolean alarms_on;
	
	public void setup(){
		Object[] args = getArguments();
		identifier = 		(String) args[0];
		speed =				(int)	 args[1];
		starting_point =	(Vertex) args[2];
		destination = 		(Vertex) args[3];
		alarms_on = 		(boolean)args[4];
		
        System.out.println("Ambulance "+ identifier +" initialized with these values:");
        System.out.println("Speed: "+ speed);
        System.out.println("Starting point: "+ starting_point.getName());
        System.out.println("Destination: "+ destination.getName());
        System.out.println("Alarms on: "+ alarms_on);
	}
}
