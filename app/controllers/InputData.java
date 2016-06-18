package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class InputData extends Controller {

	public static void index() {
		render();
	}

	public static void InputDatas(String geolocation, int rent, int numbOfBedrooms, String rented, String residenceType) {
		User user = Accounts.getCurrentUser();
		Residence findR = new Residence (user, geolocation, residenceType, rented, numbOfBedrooms, rent);
		findR.save();
		index();
	}

}
