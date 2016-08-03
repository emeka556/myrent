package controllers;

import models.Landlord;
import models.Residence;
import models.Tenant;
import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.*;

public class TenantData extends Controller {

	/**
	 * This method executed before each action call in the controller. Checks
	 * that a user has logged in. If no user logged in the user is presented
	 * with the log in screen.
	 */
	@Before
	static void checkAuthentification() {
		if (session.contains("logg_in_TenantID") == false)
			Tenants.Login();
	}

	public static void index() {

		String Tenant_eircode = null;
		Residence resident = null;

		
		Tenant tnant = Tenants.getCurrentTenant();
		List<Residence> residences = Residence.findAll();
		List<Residence> allresidences = new ArrayList<Residence>();

		for (Residence res : residences) { // res stores all the values of
											// residences, while resdiences
											// loops thru all Residence
			if (res.tenant != null) {
				if (res.tenant.equals(tnant)) {
					resident = res;

				} else {
					resident = resident; // resident is null
				}

			}
		}
		for (Residence res : residences) {
			if (res.tenant == null) {

				allresidences.add(res);
			}
		}

		if (resident == null) {
			Tenant_eircode = " ";
		} else {
			Tenant_eircode = resident.eircode;
		}

		render("Tenants/tenant.html", tnant, allresidences, Tenant_eircode);

	}

	public static void tenancyTerminate() {

		Tenant userTenant = Tenants.getCurrentTenant();
		
		List<Residence> tenantResident = Residence.findAll();

		for (Residence residence : tenantResident) {
			if (residence.tenant != null) {

			}
			if (residence.tenant.equals(userTenant))
				break;
			{
				Logger.info("The residence deleted is : " + residence.tenant.firstName + "'s resident");

				residence.tenant = null;
				userTenant.residence = null;
				userTenant.save();
				residence.save();
				index();
			}
		}
	}

	public static void changeTenancy(long newResidence) {
		Logger.info("You have updated residence: " + newResidence);

		Residence residence = Residence.findById(newResidence);

		Tenant tenant = Tenants.getCurrentTenant();

		if (tenant.residence == null) {

			residence.tenant = tenant;
			tenant.residence = residence;
			tenant.save();
			residence.save();
			index();
		}

		Logger.info("Invalid you have to terminate residency, for a new tenancy to be added");
		index();
	}

	

}

