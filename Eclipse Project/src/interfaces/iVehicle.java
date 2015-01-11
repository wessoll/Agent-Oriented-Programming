package interfaces;

import models.TomTom;

public interface iVehicle {
	public void setNavigation(TomTom navigation);
	public TomTom getNavigation();
	public void setSpeed(int speed);
	public int getSpeed();
	public int getPriority();
}
