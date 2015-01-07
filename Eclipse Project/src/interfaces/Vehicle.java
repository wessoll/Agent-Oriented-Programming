package interfaces;

import models.TomTom;

public interface Vehicle {
	public void setup();
	public void setNavigation(TomTom navigation);
	public TomTom getNavigation();
	public void setSpeed(int speed);
	public int getSpeed();
}
