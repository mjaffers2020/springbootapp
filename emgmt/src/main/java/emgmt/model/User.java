package emgmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="users", schema = "emgschema")
public class User {
	@Id
	@JsonProperty("UserID")
    @Column(name="userid")
    private String userid;
	@JsonProperty("Password")
	@Column(name="password")
    private String Password;
	@JsonProperty("FirstName")
	@Column(name="firstname")
    private String FirstName;
	@JsonProperty("LastName")
	@Column(name="lastname")
    private String LastName;
	
    public User() {
    }
	public User(String UserId ,String FirstName, String LastName,String Password) {
		this.userid = UserId;
		this.Password = Password;
		this.FirstName = FirstName;
		this.LastName = LastName;
	}

    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
}
