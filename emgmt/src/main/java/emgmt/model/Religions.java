package emgmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="religion", schema = "ems")
public class Religions {
	@Id
	@Column(name="religionid")
    private String religionId;
	
	@Column(name="description")
    private String description;


	public String getDescription() {
		return description;
	}

	public String getReligionId() {
		return religionId;
	}

	public void setReligionId(String religionId) {
		this.religionId = religionId;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
