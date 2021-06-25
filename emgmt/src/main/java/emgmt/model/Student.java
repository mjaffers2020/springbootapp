package emgmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name="student", schema = "ems" )
@SecondaryTable(name="profilebasic", schema = "ems", pkJoinColumns = @PrimaryKeyJoinColumn(name = "profilebasicid"))
//@SecondaryTable(name="address", schema = "ems", pkJoinColumns = @PrimaryKeyJoinColumn(name = "addressid"))

public class Student {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="studentid" , table="student")
    private int studentId;

	@Column(name="active"  , table="student")
    private boolean active;

	/*@Column(name="address", table="address")
    private String address;

	@Column(name="zipcode", table="address")
    private String zipcode;
	*/
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@JoinColumn(name="profilebasic.profilebasicid", referencedColumnName = "profilebasicid")
	@Id
	@Column(name="profilebasicid" , table="student")//, table="profilebasic",insertable =false, updatable = false)
    private int profileBasicId;
	
	@Column(name="firstname" , table="profilebasic")
    private String firstName;

	@Column(name="middlename" , table="profilebasic")
    private String middleName;

	@Column(name="lastname" , table="profilebasic")
	private String lastName;

	@Column(name="genderid" , table="profilebasic")
    private String genderId;

	@Column(name="dateofbirth" , table="profilebasic")
    private String dateOfBirth;

	/*
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
*/
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

	/*public int getProfileBasicId() {
		return profileBasicId;
	}

	public void setProfileBasicId(int profileBasicId) {
		this.profileBasicId = profileBasicId;
	}*/

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGenderId() {
		return genderId;
	}

	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

/*	public String getReligionId() {
		return religionId;
	}

	public void setReligionId(String religionId) {
		this.religionId = religionId;
	}

	public String getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(String nationalityId) {
		this.nationalityId = nationalityId;
	}

	@Column(name="religionid")
    private String religionId;

	@Column(name="nationalityid")
    private String nationalityId;
*/
	

}
