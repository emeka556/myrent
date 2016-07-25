package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Landlords extends Controller
{
	

  public static void index()
  {
	
    render();
  }

  public static void Signup()
  {
    render("Landlords/signup.html");
  }

  /**
   * renders login
   */
  public static void Login()
  {
	session.clear();
    render("Landlords/login.html");
  }
/**
 * clears session info logged
 */
  public static void Logout()
  {
		//Landlord landlord = getCurrentLandlord();
		//Tenant tenant = Tenants.getCurrentTenant();
		
	session.remove("logg_in_LandlordID");
	
    Welcome.index();
    
    
    
  }
/**
 *method to save user information
 *firstname, lastname, email and password in database
 */
  public static void register(String firstName, String lastName, String email, String password, String city, String county, String line1Add,String line2Add)
  {
    Logger.info(firstName + " " + lastName + " " + email + " " + password);

    Landlord landlord = new Landlord(firstName, lastName, email, password , city, county, line1Add, line2Add);

    
    landlord.save();
    Login();
    
    

  }
  
  public static void authenticate(String email, String password)
  {
    Logger.info("Attempting to authenticate with " + email + ":" + password);
    Landlord landlord = Landlord.findByEmail(email);
    if ((landlord != null) && (landlord.checkPassword(password) == true))
    {
      Logger.info("Successful authentication of  " + landlord.firstName + " " + landlord.lastName + " ");
      session.put("logg_in_LandlordID", landlord.id);
      LandlordConfiguration.index();
    }
    else
    {
      Logger.info("Authentication failed");
      Login();
    }
  }
  public static Landlord getLoginLandlord()
  {
    Landlord landlord = null;
    if (session.get("logg_in_LandlordID") != null)
    {
      String userId = session.get("logg_in_LandlordID");
      landlord = Landlord.findById(Long.parseLong(userId));
      
    }
    else
    {
      Welcome.index();
    }
    return landlord;
  }

  public static Landlord getCurrentLandlord()
  {
    String userId = session.get("logg_in_LandlordID");
    if (userId == null)
    {
      return null;
    }
    Landlord logged_in_landlord = Landlord.findById(Long.parseLong(userId));
    Logger.info("Logged in Landlord: " + logged_in_landlord.firstName);
    return logged_in_landlord;
  }

public static void redirect() {
	
		render();
	}



}
