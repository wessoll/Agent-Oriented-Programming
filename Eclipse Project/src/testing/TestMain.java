package testing;
import java.util.List;

import models.BaseCar;
import models.PassengerCar;
import models.RoadNetwork;
import models.TomTom;
import models.Vertex;

/**
 * You can use this class to test the path finding
 * @author wesley
 *
 */
public class TestMain {

	public static void main(String[] args) {
		
		BaseCar bonoMobiel = new PassengerCar();
		
		
		// First create the Road Network (this can be 
		RoadNetwork.getInstance().constructRoadNetwork();
		
		// Next create the Navigation system (this can be done individually for each car, as in every car has a TomTom)
		TomTom tomTom = new TomTom();
		
		// Now calculate the shortest routes (ofcourse this should be done individually for each car)
		Vertex[] vertices = RoadNetwork.getInstance().getVertices(); // These are basically all roads
		Vertex source = vertices[0]; // Set this to your source location
		Vertex destination = vertices[5];
		
		tomTom.computePaths(source);
		List<Vertex> theShortestPath = tomTom.getShortestPathTo(destination);
		
		System.out.println("This is your shortest path: \n" + theShortestPath); // Should print A-B-D-E-F */
		
	}
}
