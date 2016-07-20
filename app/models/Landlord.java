package models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Landlord extends Model
{
  public String firstName;
  public String lastName;
  public String email;
  public String password;
  public String city;
  public String county;
  public String line1Add;
  public String line2Add;
  
  @OneToMany(mappedBy = "from", cascade = CascadeType.ALL) 
  public List<Residence> residence = new ArrayList<>(); 


  public Landlord(String firstName, String lastName, String email, String password, String city, String county, String line1Add,String line2Add)
  {
    this.firstName = firstName;
    this.lastName  = lastName;
    this.email     = email;
    this.password  = password;
    this.line1Add = line1Add;
    this.line2Add = line2Add;
    this.city = city;
    this.county = county;
  }
  
  public static Landlord findByEmail(String email)
  {
    return find("email", email).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }
}