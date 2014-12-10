

import jade.core.*;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

public class AgentProgram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Runtime run = Runtime.instance();
		
		Profile containerProfile = new ProfileImpl();
		containerProfile.setParameter(Profile.PLATFORM_ID, "platform");
		containerProfile.setParameter(Profile.CONTAINER_NAME, "mainContainer");
		
		AgentContainer container = run.createMainContainer(containerProfile);
		
		try {
			container.createNewAgent("naam", "Auto", null).start();
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}