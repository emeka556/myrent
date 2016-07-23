package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Tenants extends Controller {
	
	  
	 
	public static void index() {
		
		
		
		Tenant tenant = Tenants.getLoginTenant();
	   
	   
	    List<Tenant> allTenants = new ArrayList();
	    allTenants = Tenant.findAll();
	   
	    {
	      if ((session.contains("logg_in_TenantID") == false))
	      { 
	    	 Tenants.Login();
	        
	      }
	      else
	      {
	    	  Tenant currentTenant = Tenants.getCurrentTenant();
				
	  	    

	  	    render(tenant, allTenants, currentTenant);
	        
	       
	      }
	    }
	    
	    
	}

	public static void Signup() {
		render("Tenants/signup.html");
	}

	
	
	/**
	 * renders login
	 */
	public static void Login() {
		session.clear(); //clears any user that is logged in
		render("Tenants/login.html");
	}

	/**
	 * clears session info logged
	 */
	
	
	
	public static void Logout() {
		session.clear();
		Welcome.index();

	}

	/**
	 * method to save user information firstname, lastname, email and password
	 * in database
	 */
	public static void register(String firstName, String lastName, String email, String password) {
		Logger.info(firstName + " " + lastName + " " + email + " " + password);

		Tenant tenant = new Tenant(firstName, lastName, email, password);

		tenant.save();
		Login();

	}
	
	  

	public static void authenticate(String email, String password) {
		Logger.info("Attempting to authenticate with " + email + ":" + password);
		Tenant tenant = Tenant.findByEmail(email);
		if ((tenant != null) && (tenant.checkPassword(password) == true)) {
			Logger.info("Successful authentication of  " + tenant.firstName + " " + tenant.lastName + " ");
			session.put("logg_in_TenantID", tenant.id);
			Tenants.index();
		} else {
			Logger.info("Authentication failed");
			Login();
		}
	}
	
	public static Tenant getLoginTenant()
	  {
	    Tenant tenant = null;
	    if (session.get("logg_in_TenantID") != null)
	    {
	      String userId = session.get("logg_in_TenantID");
	      tenant = Tenant.findById(Long.parseLong(userId));
	      
	    }
	    else
	    {
	      Welcome.index();
	    }
	    return tenant;
	  }


	public static Tenant getCurrentTenant() {
		String userId = session.get("logg_in_TenantID");
		if (userId == null) {
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
