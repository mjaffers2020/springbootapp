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
		if(repository.existsByUid(UId)) { 
			User userDetails = repository.findByUid(UId);
			String cUId = userDetails.getUid(); 
			if(!util.isNullorWhiteSpaces(cUId)) {
				User user = new User();
				user.setUserid(userDetails.getUserid());
				user.setFirstname(userDetails.getFirstname());
				user.setLastname(userDetails.getLastname());
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

			if (repository.existsByEmailaddress(user.getEmailaddress())) {
				return util.setFailureReponse(false, "User Email Already Exits", "user");
			} else if (repository.existsByphonenumber(user.getPhonenumber())) {
				return util.setFailureReponse(false, "User Phone Number Already Exits", "user");
			} else {
				user = repository.save(user);
				return util.setSucessReponse(true, user.getUserid());
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
		if (repository.existsById(userDetail.getUserid())) {
			repository.save(userDetail);
			return "Sucess";
		} else {
			System.out.println("User ID does not exits  : " + userDetail.getUserid());
			return "Error";
		}
	}

	/*
	 * public void deleteUser(String userID) throws RecordNotFoundException { if
	 * (repository.existsById(userID)) { repository.deleteById(userID); }
	 * 
	 * }
	 */ /*
	 * public String checkLoginCredentials(User credentialUI , User retrieved4mDB){
	 * String returnValue = "SUCCESS"; try {
	 * 
	 * if (retrieved4mDB != null) {
	 * System.out.println(credentialUI.getUserid()+" "+credentialUI.getPassword());
	 * System.out.println(retrieved4mDB.getUserid()+" "+retrieved4mDB.getPassword())
	 * ; if
	 * (credentialUI.getUserid().equalsIgnoreCase(retrieved4mDB.getUserid().trim())
	 * &&
	 * credentialUI.getPassword().equalsIgnoreCase(retrieved4mDB.getPassword().trim(
	 * ))){ System.out.
	 * println("User entered credentials are matched with repository data..");
	 * }else{ returnValue = "Incorrect Username / Password entered";
	 * System.out.println("Incorrect Username / Password entered.."); } }else
	 * if(credentialUI.getUserid().equalsIgnoreCase("admin")&&
	 * credentialUI.getPassword().equalsIgnoreCase("admin")){ System.out.
	 * println("User entered credentials are matched with repository data.."); }else
	 * { returnValue = "No Record found. Please register to proceed further";
	 * System.out.
	 * println("There is no User Data Record available in the Repository."); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return returnValue; }
	 */
}