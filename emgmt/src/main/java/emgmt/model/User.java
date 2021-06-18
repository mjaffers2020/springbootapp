package emgmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user", schema = "ems")
public class User {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="userid")
    private int userId;
	
	@Column(name="firstname")
    private String firstName;
	
	@Column(name="lastname")
    private String lastName;
	
	@Column(name="emailaddress") 
    private String emailAddress;
		
	@Column(name="phonenumber")
    private String phoneNumber;
	
	@Column(name="uid")
    private String uId;
	
	@Override
	public String toString() {
		return "Userid :"+userId+" Firstname :"+firstName+" Lastname :"+lastName+"\n EmailAddress : "+emailAddress+" PhoneNumber : "+phoneNumber+" UID : "+uId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

}
