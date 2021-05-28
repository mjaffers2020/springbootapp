package emgmt.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class UserJson {

    @JsonProperty("UserID")
    private String Userid;
    @JsonProperty("Password")
    private String Password;
	public UserJson() {
    }
	public UserJson(String UserId, String Password) {
		this.Userid = UserId;
		this.Password = Password;
	}

    public String getUserid() {
		return Userid;
	}

	public void setUserid(String userid) {
		Userid = userid;
	}


	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}


}