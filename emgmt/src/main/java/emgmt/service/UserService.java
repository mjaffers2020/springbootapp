package emgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import emgmt.exception.RecordNotFoundException;
import emgmt.model.User;
import emgmt.repository.UserRepository;
import emgmt.util.Utilities;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> getAllUsers() {
		System.out.println("repository.findAll() : " + repository.findAll());
		List<User> userList = repository.findAll();

		System.out.println("userList Size: " + userList);
		if (userList.size() > 0) {
			return userList;
		} else {
			return new ArrayList<User>();
		}
	}

	public List<User> findAll() {

		List<User> userDetails = (List<User>) repository.findAll();

		return userDetails;
	}


	public String getUserDetailsById(String UId) throws RecordNotFoundException {
		Utilities util = new Utilities(); 
		System.out.println("UID recieved : "+UId);
		if(repository.existsByuId(UId)) { 
			User userDetails = repository.findByuId(UId);
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

			if (repository.existsByEmailAddress(user.getEmailAddress())) {
				return util.setFailureReponse(false, "User Email Already Exits", "user");
			} else if (repository.existsByphoneNumber(user.getPhoneNumber())) {
				return util.setFailureReponse(false, "User Phone Number Already Exits", "user");
			} else {
				user = repository.save(user);
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
		if (repository.existsById(userDetail.getUserId())) {
			repository.save(userDetail);
			return "Sucess";
		} else {
			System.out.println("User ID does not exits  : " + userDetail.getUserId());
			return "Error";
		}
	}

}