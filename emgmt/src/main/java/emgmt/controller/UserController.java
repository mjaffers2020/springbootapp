package emgmt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import emgmt.model.User;
import emgmt.service.IUserService;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value="/login", method=RequestMethod.POST , headers = "Accept=application/json")
    @ResponseBody
    public String userLogin(@RequestBody User user) {
    	String loginStatus = "FAILURE";
		try {
			String userID = user.getUserid();
			User retrievedCredential = userService.getUserDetailsById(userID);
			loginStatus = userService.checkLoginCredentials(user, retrievedCredential);		

			System.out.println("returnValue : "+loginStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return loginStatus;  
    }
    @RequestMapping(value="/newUser", method=RequestMethod.POST , headers = "Accept=application/json") 
	@ResponseBody
	public String createNewUser(@RequestBody User user){
		String returnValue = "Error";
		try {
			returnValue = userService.createUser(user);
			if(!"Sucess".equalsIgnoreCase(returnValue)) {
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


}