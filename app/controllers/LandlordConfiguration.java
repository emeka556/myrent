package controllers;

import play.mvc.Before;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class LandlordConfiguration extends Controller {
	/**
	 * This method executed before each action call in the controller. Checks
	 * that a user has logged in. If no user logged in the user is presented
	 * with the log in screen.
	 */
	@Before
	static void checkAuthentification() {
		if (session.contains("logg_in_LandlordID") == false)
			Landlords.Login();
	}

	public static void index() {
		Landlord clandlord = Landlords.getLoginLandlord();
		Landlord landlord = Landlords.getCurrentLandlord();
		List<Residence> residences = Residence.findAll();
		List<Residence> allresidences = new ArrayList<Residence>();
		for (Residence res : residences) {
			if (landlord.equals(res.from)) {
				allresidences.add(res);
			}
		}
		render("Landlords/landlordconfig.html", landlord, allresidences, clandlord);
	}

	public static void residenceDelete(String eircode) {
		// Landlord landlord = Landlord.findById(Long.parseLong(userId));
		Landlord landlord = Landlords.getCurrentLandlord();

		Residence residence = Residence.findByEircode(eircode);

		Logger.info("Residence with eircode " + eircode + " has been removed"); 
		
		landlord.residence.remove(eircode); // removing the certain geolocation from the list.

		landlord.save();
		residence.delete();
		index();
	}
	
	public static void residenceEdit(String geolocation , int rent)
	  {
		 
				 
	    Residence residence = Residence.findByGeolocation(geolocation);
	    
			    
	    render("Landlords/landlordeditresidence.html", residence);
	  }
	
	public static void UpdateResidence(String eircode, int rent) 
	  { 
		Landlord landlord = Landlords.getCurrentLandlord();
	     
		Residence residence = Residence.findByEircode(eircode);
		
		
		residence.rent = rent; 
	     residence.save(); 

		render("Landlords/landlordeditresidence.html", residence, landlord);
	     
	     
	  } 

}
