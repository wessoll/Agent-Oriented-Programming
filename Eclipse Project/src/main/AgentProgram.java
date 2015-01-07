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

		RoadNetwork road_network = new RoadNetwork();
		Vertex[] vertices = road_network.getVertices();
		
		
		
		Agent bono_mobiel = new PassengerCar(vertices[0], vertices[vertices.length - 1], 120, 1);
		Agent volkswagen = new PassengerCar(vertices[0], vertices[vertices.length - 1], 120, 1);
		Agent matrix_utrecht_groningen = new MatrixBoard();
		
		try {
			// Create the Agent
			AgentController agentController = mainContainer.acceptNewAgent("BonoMobiel", bono_mobiel);

			mainContainer.acceptNewAgent("Volkswagen", volkswagen);
			mainContainer.acceptNewAgent("MatrixUtrechtGroningen", matrix_utrecht_groningen);
			
			// Start Agent
			agentController.start();

		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}
}