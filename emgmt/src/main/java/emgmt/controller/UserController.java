package emgmt.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import emgmt.model.User;
import emgmt.model.UserJson;
import emgmt.service.UserService;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value="/signin", method=RequestMethod.POST , headers = "Accept=application/json")
	@ResponseBody
	public User userLogin(@RequestBody User user) {
		User userDetails = null;
		try {
			String uID = user.getUid();
			userDetails = userService.getUserDetailsById(uID);
			if(null!=userDetails) {
				System.out.println("User Details Fouhd "+userDetails);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userDetails;  
	}
	@RequestMapping(value="/register", method=RequestMethod.POST , headers = "Accept=application/json") 
	@ResponseBody
	public String createNewUser(@RequestBody String userData){
		String returnValue = "Error";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			UserJson userJson = objectMapper.readValue(userData, UserJson.class); 
			System.out.println(userJson.toString());
			returnValue = userService.createUser(userJson);
			if("Error".equalsIgnoreCase(returnValue)) {
				returnValue = "User ID Already Exists";
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = "Exception Occured : "+e.getMessage();
		}
		return returnValue;
	}
	@RequestMapping(value="/updateUser", method=RequestMethod.POST , headers = "Accept=application/json") 
	@ResponseBody
	public String updateUser(@RequestBody User user){
		String returnValue = "Error";
		try {
			returnValue = userService.updateUser(user);
			if(!"Sucess".equalsIgnoreCase(returnValue)) {
				returnValue = "User ID does not Exists";
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = "Exception Occured : "+e.getMessage();
		}
		return returnValue;
	}
	@RequestMapping(value="/deleteUser", method=RequestMethod.POST)
	@ResponseBody
	public String deleteUser(@RequestBody User user){
		String returnValue = "Sucess";
		try {
			userService.deleteUser(user.getUserid());
			returnValue = "UserName : '"+user.getUserid()+"', Deleted Successfully";
			// deleteUser
		} catch (Exception e) {
			returnValue = "Exception : "+e.getMessage();
		}
		return returnValue;
	}

	@RequestMapping(value="/getUsers", method=RequestMethod.GET)
	@ResponseBody
	public List<User> loadAllUserData(){
		List<User> getAllUser = userService.getAllUsers();
		return getAllUser;
	}

}