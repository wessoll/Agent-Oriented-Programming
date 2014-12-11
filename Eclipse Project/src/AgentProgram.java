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
        	Object[] car_args = new Object[4];
        	car_args[0] = "car_1";
        	car_args[1] = 'a';
        	car_args[2] = new Place();
        	car_args[3] = new Place();
        	
            container.createNewAgent("auto_1", "Car", car_args).start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}