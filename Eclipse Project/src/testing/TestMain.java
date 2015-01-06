package testing;

import models.BaseCar;
import models.PassengerCar;
import models.RoadNetwork;

/**
 * You can use this class to test the path finding
 * @author wesley
 *
 */
public class TestMain {

	public static void main(String[] args) {
		
		BaseCar bonoMobiel = new PassengerCar();
		bonoMobiel.setSpeed(300);

		// Plot a course
		bonoMobiel.getNavigation().plotRoute(
				RoadNetwork.getInstance().getVertex("Amsterdam"), 
				RoadNetwork.getInstance().getVertex("Groningen"));
		
		bonoMobiel.getNavigation().printCurrentRoute();		
	}
}
