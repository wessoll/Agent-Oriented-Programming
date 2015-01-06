package main;
import models.Ambulance;
import jade.core.*;
import jade.core.Runtime;
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
        
        try {
        	Agent agent = new Ambulance();
        	
        	container.acceptNewAgent("test_Agent", agent);
        	AgentController agent_2 = container.getAgent("test_Agent");
        	System.out.println("Test: " + agent_2.getName());
        } catch (StaleProxyException e) {
            e.printStackTrace();
        } catch (ControllerException e){
        	e.printStackTrace();
        }
    }
}