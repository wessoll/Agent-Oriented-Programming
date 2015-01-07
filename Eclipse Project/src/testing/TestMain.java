package testing;

import models.Car;
import models.PassengerCar;
import models.RoadNetwork;

/**
 * You can use this class to test the path finding
 * @author wesley
 *
 */
public class TestMain {

	public static void main(String[] args) {
		
		Car bonoMobiel = new PassengerCar();
		bonoMobiel.setSpeed(300);

		// Plot a course
		bonoMobiel.getNavigation().plotRoute(
				bonoMobiel.getNavigation().getRoadnetwork().getVertex("Amsterdam"), 
				bonoMobiel.getNavigation().getRoadnetwork().getVertex("Groningen"));
		
		bonoMobiel.getNavigation().printCurrentRoute();		
	}
}
