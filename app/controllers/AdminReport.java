package controllers;

import play.mvc.Controller;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class AdminReport extends Controller {

	public static void index() {
		String filter = "(all)";
		List<Residence> residences = Residence.findAll();
		List<Tenant> tenants = Tenant.findAll();

		render("Report/AdminReport.html", residences, filter, tenants);
	}

	public static void ReportFilter(String digit) {
		int NumberChoice = Integer.parseInt(digit);
		
		Logger.info("You have selected:  " + NumberChoice);

		List<Residence> allResidences = Residence.findAll();
		List<Residence> residences = new ArrayList<Residence>();

		switch (NumberChoice) {

		case 1:
			
			for (Residence res : allResidences) {
				if (res.tenant == null) {
					residences.add(res);
				}
			}
			render("Report/AdminReport.html", residences);
			break;

		case 2:
			Collections.sort(allResidences, new ReportResidenceTypeComparator());

			residences.addAll(allResidences);
			render("Report/AdminReport.html", residences);

			break;	
			

		case 3:
			
			Collections.sort(allResidences, new ReportRentComparator());

			residences.addAll(allResidences);

			render("Report/AdminReport.html", residences);
			break;

		case 4:

			index();
			break;

		default:
			Logger.info("Something is not right, check please");
			break;

		}

	}
	public static void chart() {
		
		List<Residence> residences = Residence.findAll();
		

		render("Administrator/chartPage.html", residences);
	}

}
