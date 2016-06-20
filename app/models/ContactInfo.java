package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class ContactInfo extends Model {

	public String firstName;
	public String lastName;
	public String email;

	public String messagetxt;

	public ContactInfo(String firstName, String lastName, String email, String messagetxt) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.messagetxt = messagetxt;
	}

}
