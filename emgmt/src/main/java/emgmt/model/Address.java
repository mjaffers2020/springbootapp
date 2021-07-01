package emgmt.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="address", schema = "ems" )

public class Address {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="addressid")
    private int addressId;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="address")
	private Student student;

	@Column(name="address")
	private String address;
	
	@Column(name="cityid")
    private int cityId;
	
	@Column(name="zipcode")
	private String zipCode;
	
	@Column(name="countryid")
	private String countryId;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	
	

}
