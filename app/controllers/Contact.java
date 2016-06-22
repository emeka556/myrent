package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Contact extends Controller {
	
	 

	public static void index() {
		render();
	}

	
	public static void messageSMS(String FirstName, String Surname, String emailAdd, String messagetxt) {
		
		ContactInfo cinfo = new ContactInfo (FirstName, Surname, emailAdd, messagetxt);
		cinfo.save();
		render("Contact/Acknowledgement.html" ,cinfo);
	}

}
