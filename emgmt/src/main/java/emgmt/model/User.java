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
    private int userid;
	
	@Column(name="firstname")
    private String firstname;
	
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
	

	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	@Override
	public String toString() {
		return "Userid :"+userid+" Firstname :"+firstname+" Lastname :"+lastname+"\n EmailAddress : "+emailaddress+" PhoneNumber : "+phonenumber+" UID : "+uid;
	}

}
