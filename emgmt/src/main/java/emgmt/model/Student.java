package emgmt.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="student", schema = "ems" )

public class Student {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="studentid")
    private int studentId;

	@Column(name="active" )
    private boolean active;
	
	@Column(name="profilebasicid",insertable =false, updatable = false)
    private int profileBasicId;
	
	@OneToOne(cascade=CascadeType.ALL) 
	@MapsId
	@JoinColumn(name="profilebasicid")
	private ProfileBasic profileBasic;

	@Column(name="addressid",insertable =false, updatable = false)
    private int addressId;
	@OneToOne(cascade=CascadeType.ALL) 
	@MapsId
	@JoinColumn(name="addressid")
	private Address address;

	
	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ProfileBasic getProfileBasic() {
		return profileBasic;
	}

	public void setProfileBasic(ProfileBasic profileBasic) {
		this.profileBasic = profileBasic;
	}


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getProfileBasicId() {
		return profileBasicId;
	}

	public void setProfileBasicId(int profileBasicId) {
		this.profileBasicId = profileBasicId;
	}
}
