package models;


import interfaces.iVehicle;

import java.util.Queue;
import java.util.UUID;

import behaviours.DriveBehavior;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentController;

public class PassengerCar extends BaseCar {
	private static final long serialVersionUID = 8962335670763104350L;
	
	public PassengerCar(){
		super();
	}
	
	public void setup() {
		super.setup();
	}
	
	
}
