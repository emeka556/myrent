package models;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.Logger;
import play.db.jpa.Model;
import utils.LatLng;

@Entity
public class Residence extends Model {

	public String geolocation;
	public String postDate;
	public String residenceType;

	public int rent; // how much rent is
	public int numbOfBedrooms;
	public int numberBathrooms;
	public int area; // the area of the residence in square metres
	public String eircode;

	@ManyToOne
	public Landlord from;

	@OneToOne(mappedBy = "residence", cascade = CascadeType.ALL)
	public Tenant tenant;

	public Residence(Landlord from, String geolocation, String eircode, String residenceType, int numbOfBedrooms,
			int rent, int numberBathrooms, int area) {
		this.from = from;
		this.geolocation = geolocation;
		this.eircode = eircode;
		this.residenceType = residenceType;

		this.rent = rent;
		this.numbOfBedrooms = numbOfBedrooms;
		this.numberBathrooms = numberBathrooms;
		this.area = area;

		postDate = dateValidator();
	}

	public String dateValidator() {
		Date createOn = new Date();
		createOn = new Timestamp(createOn.getTime());
		DateFormat dformat = new SimpleDateFormat("E dd/MM/yy - KK:mm a");
		Logger.info("Date Created On " + createOn + " postDate " + dformat.format(createOn));
		return dformat.format(createOn);
	}

	public static Residence findByEircode(String eircode) {
		return find("eircode", eircode).first();
	}

	public static Residence findByGeolocation(String geolocation) {
		return find("geolocation", geolocation).first();
	}

	public LatLng RetrieveGeolocation() {

		return LatLng.toLatLng(geolocation);
	}

}
