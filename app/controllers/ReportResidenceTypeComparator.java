package controllers;

import java.util.Comparator;

import models.Residence;

public class ReportResidenceTypeComparator implements Comparator<Residence> {

	@Override
	public int compare(Residence arg0, Residence arg1) {
		
		return arg0.residenceType.compareToIgnoreCase(arg1.residenceType);
	}

}
