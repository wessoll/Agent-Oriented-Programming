package main;

public class PassengerCar extends Car {
	public PassengerCar(){
		super();
	}
	
	public void setup(){
		Object[] args = getArguments();
		identifier = 		(String) args[0];
		speed =				(int)	 args[1];
		starting_point =	(Vertex) args[2];
		destination = 		(Vertex) args[3];
		
        System.out.println("Ambulance "+ identifier +" initialized with these values:");
        System.out.println("Speed: "+ speed);
        System.out.println("Starting point: "+ starting_point.getName());
        System.out.println("Destination: "+ destination.getName());
	}
}
