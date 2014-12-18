package main;
import jade.core.*;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;


public class AgentProgram {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Runtime run = Runtime.instance();
        
        Profile containerProfile = new ProfileImpl();
        containerProfile.setParameter(Profile.PLATFORM_ID, "platform");
        containerProfile.setParameter(Profile.CONTAINER_NAME, "mainContainer");
        
        AgentContainer container = run.createMainContainer(containerProfile);
        
        try {
        	Object[] car_args = new Object[5];
        	car_args[0] = "ambulance_1";
        	car_args[1] = 100;
        	car_args[2] = new Vertex("Ergens");
        	car_args[3] = new Vertex("Ergens anders");
        	car_args[4] = true;
        	
            container.createNewAgent("vehicle_1", "main.Ambulance", car_args).start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}