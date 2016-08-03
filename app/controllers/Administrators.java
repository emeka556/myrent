package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Administrators extends Controller {
	
	  
	 
	public static void index() {
		
		Administrator ad = getCurrentAdmin(); 

		List<Tenant> tenants = Tenant.findAll();
		List<Landlord> landlords = Landlord.findAll();

		Logger.info("just landed on admin page");

		render("Administrator/AdminPage.html", ad, tenants, landlords);      
	 	    
	    
	}

	
	
	/**
	 * renders login
	 */
	public static void Login() {
		session.clear(); //clears any user that is logged in
		render("Administrator/login.html");
	}

	/**
	 * clears session info logged
	 */
	
	
	
	public static void Logout() {
		//Landlord landlord = Landlords.getCurrentLandlord();
		//Tenant tenant = getCurrentTenant();
		
		session.remove("logg_in_AdminID");
		Welcome.index();

	}

	
	  

	public static void authenticate(String email, String password) {
		Logger.info("Attempting to authenticate with " + email + ":" + password);
		Administrator admin = Administrator.findByEmail(email);
		if ((admin != null) && (admin.checkPassword(password) == true)) {
			Logger.info("Successful authentication of Administrator");
			session.put("logg_in_AdminID", admin.id);
			index();
		} else {
			Logger.info("Authentication failed");
			Login();
		}
	}
	
	public static Administrator getLoginAdmin()
	  {
	    Administrator admin = null;
	    if (session.get("logg_in_AdminID") != null)
	    {
	      String userId = session.get("logg_in_AdminID");
	      admin = Administrator.findById(Long.parseLong(userId));
	      
	    }
	    else
	    {
	      Welcome.index();
	    }
	    return admin;
	  }


	public static Administrator getCurrentAdmin() {
		String userId = session.get("logg_in_AdminID");
		if (userId == null) {
			return null;
		}
		Administrator logged_in_admin = Administrator.findById(Long.parseLong(userId));
		Logger.info("Logged in Admin: " + logged_in_admin.email);
		return logged_in_admin;
	}
	
	public static void getCordinates() {
		int flag = 0;
		List<Residence> Residences = Residence.findAll();
		List<List<String>> Array_json = new ArrayList<List<String>>();

		for (Residence res : Residences) {

			String ID = Long.toString(res.id);
			String lon = Double.toString(res.RetrieveGeolocation().getLongitude());
			String lat = Double.toString(res.RetrieveGeolocation().getLatitude());
			String Nameoftenant = (res.tenant == null) ?   "no tenant yet" : res.tenant.firstName;			
			String Eircode = res.eircode;

			Array_json.add(flag,
					Arrays.asList(ID, lat, lon, Nameoftenant, Eircode));
			flag++;
		}

		renderJSON(Array_json);

	}



}
