package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.Landlord;



import java.util.List;
import java.util.ArrayList;

import javax.persistence.OneToMany;
import javax.persistence.Entity;


import play.Logger;
import play.db.jpa.Model;
import play.db.jpa.Blob;

public class LandlordEditProfile extends Controller {
	public static void index() {
		String userId = session.get("logged_in_userid");

		if (userId != null) {
			render();
		}

		else {
			Landlords.redirect();
		}
	}

	public static void changeDetails(String firstName, String lastName, String city, String county, String line1Add, String line2Add) {
		Logger.info("New Landlord details are: " + firstName + " " + lastName + " " + city + " " + county + " " );
		String userId = session.get("logged_in_userid");
		Landlord landlord = Landlord.findById(Long.parseLong(userId));
		landlord.firstName = firstName;
		landlord.lastName = lastName;
		landlord.line1Add = line1Add;
		landlord.line2Add = line2Add;
		landlord.city = city;
		landlord.county = county;
		
		
		Landlord.findById(Long.parseLong(userId));
		landlord.save();
		InputData.index();
	}
}
