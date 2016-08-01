package controllers;

import java.util.Comparator;

import models.Residence;

public class ReportComparator implements Comparator<Residence>{

	@Override
	public int compare(Residence p0, Residence p1) {
		
		return Integer.compare(p0.rent, p1.rent);
	}

	
}
