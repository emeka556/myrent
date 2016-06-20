package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Contact extends Controller {
	
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

	
	public static void messageSMS(String FirstName, String Surname, String emailAdd, String messagetxt) {
		
		ContactInfo cinfo = new ContactInfo (FirstName, Surname, emailAdd, messagetxt);
		cinfo.save();
		render("Contact/Acknowledgement.html" ,cinfo);
	}

}
