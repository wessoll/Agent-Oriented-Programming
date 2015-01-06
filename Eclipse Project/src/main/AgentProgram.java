package main;
import jade.core.*;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
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
        
        Agent agent = new Ambulance();
        
        try {
        	container.acceptNewAgent("test_Agent", agent);
        } catch (ControllerException e){
        	e.printStackTrace();
        }
    }
}