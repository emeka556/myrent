package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class InputData extends Controller {

	public static void index() {
		render();
	}

	public static void InputData(String geolocation, int rent, int numbOfBedrooms, String status, String residenceType) {
		User user = Accounts.getCurrentUser();
		Residence findR = new Residence (user, geolocation, residenceType, status, numbOfBedrooms, rent);
		findR.save();
		index();
	}

}
