package main;

import models.MatrixBoard;
import models.PassengerCar;
import models.RoadNetwork;
import models.Vertex;
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
        Runtime rt = Runtime.instance(); // Get Jade Runtime
        
        // Create a default Profile
        Profile defaultProfile = new ProfileImpl();
        defaultProfile.setParameter(Profile.PLATFORM_ID, "platform");
        defaultProfile.setParameter(Profile.CONTAINER_NAME, "mainContainer");
        
        // Create the Main Container
        ContainerController mainContainer = rt.createMainContainer(defaultProfile);
 
       try {
    	   // Create the Agents
        	AgentController agentController = mainContainer.createNewAgent("InfoAgent", "models.RoadNetwork", null);
        	
        	AgentController agentController2 = mainContainer.createNewAgent("Car A", "models.PassengerCar",  null);
        	/*AgentController agentController3 = mainContainer.createNewAgent("Car B", "models.PassengerCar",  null);
        	AgentController agentController4 = mainContainer.createNewAgent("Car C", "models.PassengerCar",  null);
        	AgentController agentController5 = mainContainer.createNewAgent("Car D", "models.PassengerCar",  null);
        	AgentController agentController6 = mainContainer.createNewAgent("Car E", "models.PassengerCar",  null);
        	AgentController agentController7 = mainContainer.createNewAgent("Car F", "models.PassengerCar",  null);
        	AgentController agentController8 = mainContainer.createNewAgent("Car G", "models.PassengerCar",  null);
        	AgentController agentController9 = mainContainer.createNewAgent("Car H", "models.PassengerCar",  null);*/
        	
        	// Start Agent
        	agentController.start();
        	agentController2.start();
        	//agentController3.start();
        	//agentController4.start();
        	//agentController5.start();
        	//agentController6.start();
        	//agentController7.start();
        	//agentController8.start();
        	//agentController9.start();

       } catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
