package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Residence;
import models.Landlord;
import play.mvc.Before;
import play.mvc.Controller;
import utils.Circle;
import utils.Geodistance;
import utils.LatLng;

public class Report extends Controller
{
  /**
   * This method executed before each action call in the controller.
   * Checks that a user has logged in.
   * If no user logged in the user is presented with the log in screen.
   */
  @Before
  static void checkAuthentification()
  {
    if(session.contains("logg_in_LandlordID") == false)
      Landlords.Login();
  }

  /**
   *  Generates a Report instance relating to all residences within circle
   * @param radius    The radius (metres) of the search area
   * @param latcenter The latitude of the centre of the search area
   * @param lngcenter The longtitude of the centre of the search area
   */
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
      if (Geodistance.inCircle(residenceLocation, circle))
      {
        residences.add(res);
      }
    }
    render("Report/renderReport.html", user, circle, residences);
  }

  /**
   * Render the views/ReporController/index.html template
   * This presents a map and resizable circle to indicate a search area for residences
   */
  public static void index()
  {
    render();
  }
}