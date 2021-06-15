package emgmt.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emgmt.common.exception;
import emgmt.common.response;
import emgmt.exception.RecordNotFoundException;
import emgmt.model.User;
import emgmt.model.UserJson;
import emgmt.repository.UserRepository;
import emgmt.util.Utilities;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	public List<User> getAllUsers()
	{
		System.out.println("repository.findAll() : "+repository.findAll());
		List<User> userList = repository.findAll();

		System.out.println("userList Size: "+userList);
		if(userList.size() > 0) {
			return userList;
		} else {
			return new ArrayList<User>();
		}
	}


	public List<User> findAll() {

		List<User> userDetails = (List<User>) repository.findAll();

		return userDetails;
	}
	public User getUserDetailsById(String UId) throws RecordNotFoundException
	{
		Utilities util =  new Utilities();
		System.out.println("UID recieved : "+UId);

		if(repository.existsById(UId)) {
			User credentials = repository.getOne(UId);
			String cUId = credentials.getUid();
			if(!util.isNullorWhiteSpaces(cUId)) {
				System.out.println("UID : "+cUId+" , found in the Repository.. ");
				return credentials;
			} else {
				System.out.println("There is no such UID found in the Repository.. ");
				return null;
			}
		}else{
			System.out.println("There is no such  UID found in the Repository.. ");
			return null;
		}

	}
	public String createUser(UserJson userJson)
	{
		Utilities util = new Utilities();
		try {
			
			if(repository.existsByEmailaddress(userJson.getEmailaddress())) {
				return util.setFailureReponse(false, "User Email Already Exits");
			}else if(repository.existsByphonenumber(userJson.getPhonenumber())) {
				return util.setFailureReponse(false, "User Phone Number Already Exits");
			}else {
				User userDetail  =  new User();
				BeanUtils.copyProperties(userJson,userDetail);
				userDetail = repository.save(userDetail);
				return util.setSucessReponse(true, userDetail.getUserid());
			}
		}catch(Exception e) {
			e.printStackTrace();
			return util.setFailureReponse(false, e);
		}

	}
	public String updateUser(User userDetail)
	{
		if(repository.existsById(userDetail.getUserid()))
		{
			repository.save(userDetail);
			return "Sucess";
		} else {
			System.out.println("User ID does not exits  : "+userDetail.getUserid());
			return "Error";
		}
	}

	public void deleteUser(String userID) throws RecordNotFoundException
	{
		if (repository.existsById(userID)) {
			repository.deleteById(userID);
		}

	}
	/*
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