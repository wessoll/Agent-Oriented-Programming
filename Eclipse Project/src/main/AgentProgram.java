package main;
import jade.core.*;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.ContainerController;
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
        
        Agent agen
       try {
    	   // Create the Agent
        	AgentController agentController = mainContainer.createNewAgent(
        			"Bono Mobiel", 
        			"models.PassengerCar", 
        			null); 
        	
        	mainContainer.createNewAgent(
        			"Toyota", 
        			"models.PassengerCar", 
        			null);
        	
        	mainContainer.createNewAgent(
        			"Volkswagen",
        			"models.PassengerCar",
        			null);
        	     
        	mainContainer.createNewAgent(
        			"MaxtrixUtrechtGroningen", 
        			"models.MatrixBoard", 
        			null);
        	// Start Agent
        	agentController.start();

        
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}