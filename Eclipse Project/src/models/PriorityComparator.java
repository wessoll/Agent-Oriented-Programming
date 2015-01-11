package models;

import interfaces.iVehicle;

import java.io.Serializable;
import java.util.Comparator;

public class PriorityComparator implements Comparator<iVehicle>, Serializable {

	@Override
	public int compare(iVehicle o1, iVehicle o2) {
		// TODO Auto-generated method stub
		if (o1.getPriority() < o2.getPriority())
        {
            return -1;
        }
        if (o1.getPriority() > o2.getPriority())
        {
            return 1;
        }
		return 0;
	}

}
