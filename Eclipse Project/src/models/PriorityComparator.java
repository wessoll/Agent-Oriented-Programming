package models;

import interfaces.Vehicle;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Vehicle>{

	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		// TODO Auto-generated method stub
		if (o1.getPiority() < o2.getPiority())
        {
            return -1;
        }
        if (o1.getPiority() > o2.getPiority())
        {
            return 1;
        }
		return 0;
	}

}
