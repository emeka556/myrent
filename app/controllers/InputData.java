package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class InputData extends Controller {
	
	 /**
	   * This method executed before each action call in the controller.
	   * Checks that a user has logged in.
	   * If no user logged in the user is presented with the log in screen.
	   */
	  @Before
	  static void checkAuthentification()
	  {
	    if(session.contains("logged_in_userid") == false)
	      Accounts.Login();
	  }

	public static void index() {
		render();
	}

	public static void InputDatas(String geolocation, int rent, int numbOfBedrooms, String rented, String residenceType, int numberBathrooms, int area) {
		User user = Accounts.getCurrentUser();
		Residence findR = new Residence (user, geolocation, residenceType, rented, numbOfBedrooms, rent, numberBathrooms, area);
		findR.save();
		index();
	}

}
