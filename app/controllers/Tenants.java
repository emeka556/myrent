package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Tenants extends Controller
{

  public static void index()
  {
	  Tenant tenant = Tenants.getCurrentTenant();
    render(tenant);
  }

  public static void Signup()
  {
    render("Tenants/signup.html");
  }

  /**
   * renders login
   */
  public static void Login()
  {
    render("Tenants/login.html");
  }
/**
 * clears session info logged
 */
  public static void Logout()
  {
    session.clear();
    Welcome.index();
    
  }
/**
 *method to save user information
 *firstname, lastname, email and password in database
 */
  public static void register(String firstName, String lastName, String email, String password)
  {
    Logger.info(firstName + " " + lastName + " " + email + " " + password);

    Tenant tenant = new Tenant(firstName, lastName, email, password );

    

    tenant.save();
    Login();
    
    

  }
  
  public static void authenticate(String email, String password)
  {
    Logger.info("Attempting to authenticate with " + email + ":" + password);
    Tenant tenant = Tenant.findByEmail(email);
    if ((tenant != null) && (tenant.checkPassword(password) == true))
    {
      Logger.info("Successful authentication of  " + tenant.firstName + " " + tenant.lastName + " ");
      session.put("logged_in_userid", tenant.id);
      Tenants.index();
    }
    else
    {
      Logger.info("Authentication failed");
      Login();
    }
  }

  public static Tenant getCurrentTenant()
  {
    String userId = session.get("logged_in_userid");
    if (userId == null)
    {
      return null;
    }
    Tenant logged_in_tenant = Tenant.findById(Long.parseLong(userId));
    Logger.info("Logged in Tenant: " + logged_in_tenant.firstName);
    return logged_in_tenant;
  }

public static void redirect() {
	
		render();
	}



}
