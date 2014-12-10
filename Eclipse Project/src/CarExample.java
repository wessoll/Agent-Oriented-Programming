import java.util.ArrayList;
import java.util.List;


public class CarExample {

	private String name;
	private int speedLimit; // max speed
	private TomTom tomTom; // navigation system
	
	public CarExample() {
		// Car settings
		this.name = "Bono Mobiel";
		this.speedLimit = 45;
		this.tomTom = new TomTom();
		
		// Environment settings
		RoadNetwork.getInstance().constructRoadNetwork(); // Make sure the Road exists
		Vertex[] vertices = RoadNetwork.getInstance().getVertices();
		
		// @todo We should get a copy of the vertices, so we can set our own weights.

		// Determine the weights for all edges (from what we "remember")
		vertices[1].getAdjacencies()[1].setWeight(100); // example. we say that the second edge of the second vertex has a higher weight for whatever reason (e.g. incident?)
		
		// Determine the shortes path
		tomTom.computePaths(vertices[0]); // start from A
		List<Vertex> theShortestPath = tomTom.getShortestPathTo(vertices[5]); // go to F
		
		System.out.println("This is your shortestt path: \n" + theShortestPath); // Should print A-B-C-F instead of A-B-D-E-F
		
	}
	
	
	
}
