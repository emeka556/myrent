package controllers;

import models.Landlord;
import models.Residence;
import play.mvc.Before;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;


public class LandlordConfiguration extends Controller {
	/**
	   * This method executed before each action call in the controller.
	   * Checks that a user has logged in.
	   * If no user logged in the user is presented with the log in screen.
	   */
	  @Before
	  static void checkAuthentification()
	  {
	    if(session.contains("logged_in_userid") == false)
	      Landlords.Login();
	  }

	public static void index() {
		Landlord landlord = Landlords.getCurrentLandlord();
		render("Landlords/landlordconfig.html",landlord);
	}

	
}
