package models;



import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.Logger;
import play.db.jpa.Model;

@Entity
public class Residence extends Model {

	public String rented; //rented or vacant
	public String geolocation;
	public String postDate;
	public String residenceType;
	
	public int rent; //how much rent is
	public int numbOfBedrooms;
	
	@ManyToOne
	public User from;
	
	public Residence (User from, String geolocation, String residenceType, 
			String rented, int numbOfBedrooms, int rent  ){
		
		this.from = from;
		this.geolocation = geolocation;
		this.residenceType = residenceType;
		this.rented = rented;
		this.rent = rent;
		this.numbOfBedrooms = numbOfBedrooms;
		postDate = dateValidator();
	}

	public String dateValidator() {
		Date createOn = new Date();
		createOn = new Timestamp(createOn.getTime());
		DateFormat dformat = new SimpleDateFormat("E dd/MM/yy - KK:mm a");
		Logger.info("Date Created On " + createOn + " postDate " + dformat.format(createOn));
		return dformat.format(createOn);
}
}
