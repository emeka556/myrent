package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class InputData extends Controller {

	/**
	 * This method executed before each action call in the controller. Checks
	 * that a user has logged in. If no user logged in the user is presented
	 * with the log in screen.
	 */
	@Before
	static void checkAuthentification() {
		if (session.contains("logged_in_userid") == false)
			Landlords.Login();
	}

	public static void index() {
		Landlord landlord = Landlords.getCurrentLandlord();
		render(landlord);
	}

	public static void InputDatas(String geolocation, String eircode, int rent, int numbOfBedrooms,
			String residenceType, int numberBathrooms, int area) {
		Landlord user = Landlords.getCurrentLandlord();
		Residence findR = new Residence(user, geolocation, eircode, residenceType, numbOfBedrooms, rent,
				numberBathrooms, area);
		findR.save();
		index();
	}

}
