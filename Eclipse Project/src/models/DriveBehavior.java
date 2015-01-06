package models;

import interfaces.IVehicle;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

/**
 * The behavior that moves the car forward, it is a cycle behavior and is called every x interval
 * @author wesley
 *
 */
public class DriveBehavior extends Behaviour {

	private static final long serialVersionUID = 4555157319361129456L;

	public DriveBehavior(Agent agent) {
		super(agent);
	}
	
	
	/**
	 * What to do in this action
	 */
	@Override
	public void action() {
		// @todo move the car forward one place
		IVehicle car = (IVehicle)this.myAgent; // Parse the Agent to Vehicle so we can use the Vehicle operations
	}

	/**
	 * Checks whether we are done or not
	 */
	@Override
	public boolean done() {
		// true should not be returned until this behavior is finished (e.g. the car reached its destination)
		
		return true;
	}

	
	
	
	
	
	
	
}
