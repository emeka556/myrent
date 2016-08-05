package controllers;

import play.mvc.Controller;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class AdminReport extends Controller{
	
	public static void index()
	{
		String filter = "(all)";
		List<Residence> residences = Residence.findAll();
		List<Tenant> tenants = Tenant.findAll();
		
		render("Administrator/AdminReport.html",residences, filter, tenants );
	}
	
}
