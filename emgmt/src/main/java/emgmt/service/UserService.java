package emgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import emgmt.common.Constants;
import emgmt.exception.RecordNotFoundException;
import emgmt.model.Genders;
import emgmt.model.Nationalities;
import emgmt.model.Religions;
import emgmt.model.Student;
import emgmt.model.User;
import emgmt.repository.GenderRepository;
import emgmt.repository.NationalitiesRepository;
import emgmt.repository.ReligionsRepository;
import emgmt.repository.StudentRepository;
import emgmt.repository.UserRepository;
import emgmt.responsepacket.StudentBasicProfileResponse;
import emgmt.util.Utilities;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GenderRepository genderRepository;

	@Autowired
	private NationalitiesRepository nationalitiesRepository;

	@Autowired
	private ReligionsRepository religionsRepository;

	@Autowired
	private StudentRepository studentRepository;

	public List<User> getAllUsers() {
		List<User> userList = userRepository.findAll();
		System.out.println("getAllUsers: " + userList);
		if (userList.size() > 0) {
			return userList;
		} else {
			return null;
		}
	}

	public List<Genders> getAllGenders() {
		List<Genders> gendersList = genderRepository.findAll();
		System.out.println("getAllGenders: " + gendersList);
		if (gendersList.size() > 0) {
			return gendersList;
		} else {
			return null;
		}
	}
	public List<Nationalities> getAllNationalities() {
		List<Nationalities> nationalityList = nationalitiesRepository.findAll();
		System.out.println("getAllNationalities: " + nationalityList);
		if (nationalityList.size() > 0) {
			return nationalityList;
		} else {
			return null;
		}
	}

	public List<Religions> getAllReligions() {
		List<Religions> religionsList = religionsRepository.findAll();
		System.out.println("getAllReligions: " + religionsList);
		if (religionsList.size() > 0) {
			return religionsList;
		} else {
			return null;
		}
	}

	public List<Student> getAllProfileBasic() {
		List<Student> profileBasicList = studentRepository.findAll();
		System.out.println("getAllProfileBasic: " + profileBasicList);
		if (profileBasicList.size() > 0) {
			return profileBasicList;
		} else {
			return null;
		}
	}
	public String getStudentDetailsById(int studentid) throws RecordNotFoundException {
		Utilities util = new Utilities(); 
		System.out.println("Student ID recieved : "+studentid);
		if(studentRepository.existsByStudentId(studentid)) { 
			Student studentrepo = studentRepository.findByStudentId(studentid);
			Integer cStudentId = studentrepo.getStudentId();
			System.out.println("Student Id : "+cStudentId+" , found in the Repository.. "); 
			return 	util.setSucessReponse(true, studentrepo); 
		}else{
			System.out.println("There is no such  UID found in the Repository.. ");
			return util.setFailureReponse(false, "Uid does not Exits", "user");
		}
	}

	public String createStudent(Student student) {
		Utilities util = new Utilities();
		try {
			Student studentrepo = studentRepository.save(student);
			return util.setSucessReponse(true, studentrepo.getStudentId());
		} catch (DataAccessException de) {
			return util.setFailureReponse(false, de);
		} catch (Exception e) {
			e.printStackTrace();
			return util.setFailureReponse(false, e);
		}
	}

	public StudentBasicProfileResponse validateRoutePaths(List<String> routePaths) {
		StudentBasicProfileResponse  studentBasicProfileResponse  = new StudentBasicProfileResponse();
		for(String routePath :  routePaths) {
			System.out.println("-----------------------------"+routePath);
			if(Constants.GENDERS.equalsIgnoreCase(routePath) || Constants.GENDERS1.equalsIgnoreCase(routePath) || Constants.GENDERS2.equalsIgnoreCase(routePath)) {
				List<Genders> genderList = getAllGenders();
				if(null!=genderList && !genderList.isEmpty()) {
					studentBasicProfileResponse.setGenders(genderList);
				}
			}else if(Constants.NATIONALITIES.equalsIgnoreCase(routePath)|| Constants.NATIONALITIES1.equalsIgnoreCase(routePath) || Constants.NATIONALITIES2.equalsIgnoreCase(routePath)) {
				List<Nationalities> nationlityList = getAllNationalities();
				if(null!=nationlityList && !nationlityList.isEmpty()) {
					studentBasicProfileResponse.setNationalities(nationlityList);
				}
			}else if(Constants.RELIGIONS.equalsIgnoreCase(routePath) || Constants.RELIGIONS1.equalsIgnoreCase(routePath) || Constants.RELIGIONS2.equalsIgnoreCase(routePath)) {
				List<Religions> religionList = getAllReligions();
				if(null!=religionList && !religionList.isEmpty()) {
					studentBasicProfileResponse.setReligions(religionList);
				}
			}else if(Constants.STUDENT_BASIC_PROFILE.equalsIgnoreCase(routePath) || Constants.STUDENT_BASIC_PROFILE1.equalsIgnoreCase(routePath)
					|| Constants.STUDENT_BASIC_PROFILE2.equalsIgnoreCase(routePath) || Constants.STUDENT_BASIC_PROFILE3.equalsIgnoreCase(routePath)) {

			}

		}
		return studentBasicProfileResponse;
	}

	public String getUserDetailsById(String UId) throws RecordNotFoundException {
		Utilities util = new Utilities(); 
		System.out.println("UID recieved : "+UId);
		if(userRepository.existsByuId(UId)) { 
			System.out.println("UID found");
			User userDetails = userRepository.findByuId(UId);
			String cUId = userDetails.getuId(); 
			if(!util.isNullorWhiteSpaces(cUId)) {
				User user = new User();
				user.setUserId(userDetails.getUserId());
				user.setFirstName(userDetails.getFirstName());
				user.setLastName(userDetails.getLastName());
				System.out.println("UID : "+cUId+" , found in the Repository.. "); 
				return 	util.setSucessReponse(true, user); 
			} else {
				System.out.println("There is no such UID found in the Repository.. "); 
				return util.setFailureReponse(false, "Uid does not Exits", "user");
			}
		}else{
			System.out.println("There is no such  UID found in the Repository.. ");
			return util.setFailureReponse(false, "Uid does not Exits", "user");
		}

	}

	public String createUser(User user) {
		Utilities util = new Utilities();
		try {

			if (userRepository.existsByEmailAddress(user.getEmailAddress())) {
				return util.setFailureReponse(false, "User Email Already Exits", "user");
			} else if (userRepository.existsByphoneNumber(user.getPhoneNumber())) {
				return util.setFailureReponse(false, "User Phone Number Already Exits", "user");
			} else {
				user = userRepository.save(user);
				return util.setSucessReponse(true, user.getUserId());
			}
		} catch (DataAccessException de) {
			System.out.println("--------------------" + de.getLocalizedMessage());
			System.out.println("--------------------" + de.getMostSpecificCause().getMessage());
			return util.setFailureReponse(false, de);
		} catch (Exception e) {
			e.printStackTrace();
			return util.setFailureReponse(false, e);
		}

	}

	public String updateUser(User userDetail) {
		if (userRepository.existsById(userDetail.getUserId())) {
			userRepository.save(userDetail);
			return "Sucess";
		} else {
			System.out.println("User ID does not exits  : " + userDetail.getUserId());
			return "Error";
		}
	}
	public void deleteUser(String uid) throws RecordNotFoundException
	{
		User user = userRepository.findByuId(uid);   
		if (user != null) {
			userRepository.delete(user);
		}

	}

}