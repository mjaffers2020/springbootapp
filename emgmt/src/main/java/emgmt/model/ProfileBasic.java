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
@Table(name="profilebasic", schema = "ems" )

public class ProfileBasic {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="profilebasicid")
	private int profileBasicId;
	@OneToOne(cascade=CascadeType.ALL, mappedBy="profileBasic")
	private Student student;

	@Column(name="firstname" )
	private String firstName;

	@Column(name="middlename")
	private String middleName;

	@Column(name="lastname")
	private String lastName;

	@Column(name="genderid" )
	private String genderId;

	@Column(name="dateofbirth" )
	private java.sql.Date dateOfBirth;

	@Column(name="religionid" )
	private String religionId;

	@Column(name="nationalityid" )
	private String nationalityId;

	public String getReligionId() {
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

	public int getProfileBasicId() {
		return profileBasicId;
	}

	public void setProfileBasicId(int profileBasicId) {
		this.profileBasicId = profileBasicId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public java.sql.Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(java.sql.Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


}
