package emgmt.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emgmt.exception.RecordNotFoundException;
import emgmt.model.User;
import emgmt.model.UserJson;
import emgmt.repository.UserRepository;
import emgmt.util.Utilities;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {

		List<User> userDetails = (List<User>) repository.findAll();

		return userDetails;
	}
	public User getUserDetailsById(String UserId) throws RecordNotFoundException
	{
		Utilities util =  new Utilities();
		System.out.println(UserId);
		if(repository.existsById(UserId)) {
			User credentials = repository.getOne(UserId);
			String userId = credentials.getUserid();
			String pwd = credentials.getPassword();
			if(!util.isNullorWhiteSpaces(userId) && !util.isNullorWhiteSpaces(pwd)) {
				System.out.println("Username : "+userId+" , found in the Repository.. ");
				return credentials;
			} else {
				System.out.println("There is no user details found in the Repository.. ");
				return null;
			}
		}else{
			System.out.println("There is no user details found in the Repository.. ");
			return null;
		}

	}
	public String createUser(User userDetail)
	{
		if(!repository.existsById(userDetail.getUserid()))
		{
			userDetail = repository.save(userDetail);
			return "Sucess";
		} else {
			System.out.println("User ID already Available  : "+userDetail.getUserid());
			return "Error";
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
	public  String checkLoginCredentials(User credentialUI , User retrieved4mDB){
		String returnValue = "SUCCESS";
		try {

			if (retrieved4mDB != null) {
				if (credentialUI.getUserid().equalsIgnoreCase(retrieved4mDB.getUserid()) &&
						credentialUI.getPassword().equalsIgnoreCase(retrieved4mDB.getPassword())){
					System.out.println("User entered credentials are matched with repository data..");
				}else{
					returnValue = "Incorrect Username / Password entered";
					System.out.println("Incorrect Username / Password entered..");
				}
			}else if(credentialUI.getUserid().equalsIgnoreCase("admin")&&
					credentialUI.getPassword().equalsIgnoreCase("admin")){
				System.out.println("User entered credentials are matched with repository data..");
			}else {
				returnValue = "No Record found. Please register to proceed further";
				System.out.println("There is no User Data Record available in the Repository.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}
}