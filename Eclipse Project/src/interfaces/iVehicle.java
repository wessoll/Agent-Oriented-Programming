package interfaces;

import models.Edge;
import models.TomTom;

public interface iVehicle {
	public void setNavigation(TomTom navigation);
	public TomTom getNavigation();
	public void setSpeed(int speed);
	public int getSpeed();
	public int getPriority();
	public Edge getCurrentEdge();
	public void setCurrentEdge(Edge currentEdge);
	public Edge getPreviousEdge();
	public void setPreviousEdge(Edge previousEdge);
	
	
}
