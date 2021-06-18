package emgmt.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import emgmt.model.User;
import emgmt.service.UserService;
import emgmt.util.Utilities;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value="/signin", method=RequestMethod.POST , headers = "Accept=application/json")
	@ResponseBody
	public String userLogin(@RequestBody String userData) {
		String returnValue = null;
		Utilities  util =  new Utilities();
		try {
			User user  = util.setJsonToObject(userData);

			String uID = user.getUid();
			returnValue = userService.getUserDetailsById(uID);
			if(null==returnValue) {
				System.out.println("User Details not Fouhd "+returnValue);
				returnValue = util.setFailureReponse(false, "Uid does not Exits", "user");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnValue;  
	}
	@GetMapping(value="/user" )
	@ResponseBody
	public String getUser(@RequestParam(required=true) Map<String, String> params) {

		String returnValue = null;
		Utilities  util =  new Utilities();
		System.out.println(params);

		try {
			if(params.size()==1) {
				System.out.println(params.keySet().toArray()[0].toString().toLowerCase());
				String uID = params.get(params.keySet().toArray()[0].toString());
				returnValue = userService.getUserDetailsById(uID);
				if(null==returnValue) {
					System.out.println("User Details not Fouhd "+returnValue);
					returnValue = util.setFailureReponse(false, "Uid does not Exits", "user");
				}
			}else {
				System.out.println("Invalid number of params found for Uid "+returnValue);
				returnValue = util.setFailureReponse(false, "Invalid number of Params found for Uid", "user");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnValue;  
	}

	@RequestMapping(value="/register", method=RequestMethod.POST , headers = "Accept=application/json") 
	@ResponseBody
	public String createNewUser(@RequestBody String userData){
		String returnValue = "Error";
		try {
			Utilities  util =  new Utilities();
			User user  = util.setJsonToObject(userData);
			returnValue = userService.createUser(user);
			if("Error".equalsIgnoreCase(returnValue)) {
				returnValue = util.setFailureReponse(false, "User Email Already Exits", "user");
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
			//userService.deleteUser(user.getUserid());
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