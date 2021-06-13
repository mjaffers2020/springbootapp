package emgmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users", schema = "emgschema")
public class User {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="userid")
    private String userid;
	
	@Column(name="firstname")
    private String FirstName;
	
	@Column(name="lastname")
    private String lastname;
	
	@Column(name="emailaddress") 
    private String emailaddress;
		
	@Column(name="phonenumber")
    private String phonenumber;
	
	@Column(name="uid")
    private String uid;
	
    public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public User() {
    }
	
    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	@Override
	public String toString() {
		return "Userid :"+userid+" Firstname :"+FirstName+" Lastname :"+lastname+"\n EmailAddress : "+emailaddress+" PhoneNumber : "+phonenumber+" UID : "+uid;
	}
}
