package emgmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="profilebasic", schema = "ems")
public class ProfileBasic {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="profilebasicid")
    private int profileBasicId;
	
	@Column(name="firstname")
    private String firstName;

	@Column(name="middlename")
    private String middleName;

	@Column(name="lastname")
	private String lastName;

	@Column(name="genderid")
    private String genderId;

	@Column(name="dateofbirth")
    private String dateOfBirth;

	@Column(name="religionid")
    private String religionId;

	@Column(name="nationalityid")
    private String nationalityId;

	

}
