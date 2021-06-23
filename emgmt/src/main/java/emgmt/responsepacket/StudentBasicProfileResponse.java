package emgmt.responsepacket;

import java.util.List;

import emgmt.model.Genders;
import emgmt.model.Nationalities;
import emgmt.model.Religions;

public class StudentBasicProfileResponse {
	List<Religions> religions;
	List<Nationalities> nationalities;
	List<Genders> genders;
	public List<Religions> getReligions() {
		return religions;
	}
	public void setReligions(List<Religions> religions) {
		this.religions = religions;
	}
	public List<Nationalities> getNationalities() {
		return nationalities;
	}
	public void setNationalities(List<Nationalities> nationalities) {
		this.nationalities = nationalities;
	}
	public List<Genders> getGenders() {
		return genders;
	}
	public void setGenders(List<Genders> genders) {
		this.genders = genders;
	}
}
