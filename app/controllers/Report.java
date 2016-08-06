package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



import models.Residence;
import models.Tenant;
import models.Landlord;
import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;
import utils.Circle;
import utils.Geodistance;
import utils.LatLng;

public class Report extends Controller
{
 
  /**
   *  Generates a Report instance relating to all residences within circle
   * @param radius    The radius (metres) of the search area
   * @param latcenter The latitude of the centre of the search area
   * @param lngcenter The longtitude of the centre of the search area
   */


  /**
   * Render the views/ReporController/index.html template
   * This presents a map and resizable circle to indicate a search area for residences
   */
 
  
  @Before
	static void checkAuthentification() {
		if (session.contains("logg_in_LandlordID") == false)
			Landlords.Login();
	}
  
  public static void index()
  {
	  		render();
	       
	
  }
  public static void generateReport(double radius, double latcenter, double lngcenter)
  {
	  
    // All reported residences will fall within this circle
    Circle circle = new Circle(latcenter, lngcenter, radius);
    Landlord user = Landlords.getCurrentLandlord();
    List<Residence> residences = new ArrayList<Residence>();
    // Fetch all residences and filter out those within circle
    List<Residence> residencesAll = Residence.findAll();
    for (Residence res : residencesAll)
    {
      
      
      LatLng residenceLocation = LatLng.toLatLng(res.geolocation);
      if (Geodistance.inCircle(residenceLocation, circle)  )
      {
        residences.add(res);
        
      }
    }
    render("Report/landlordReport.html", user, circle, residences);
  
  }
  

}
