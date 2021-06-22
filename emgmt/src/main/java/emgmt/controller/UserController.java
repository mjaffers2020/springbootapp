package emgmt.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import emgmt.model.User;
import emgmt.service.UserService;
import emgmt.util.Utilities;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value="/user/{uid}" )
	@ResponseBody
	public String getUser(@PathVariable("uid") String uid) {

		String returnValue = null;
		Utilities  util =  new Utilities();
		System.out.println(uid);

		try {
			if(null!=uid && !"".equals(uid)) {
				returnValue = userService.getUserDetailsById(uid);
				if(null==returnValue) {
					System.out.println("User Details not Fouhd "+returnValue);
					returnValue = util.setFailureReponse(false, "Uid does not Exits", "user");
				}
			}else {
				System.out.println("uid is empay or null "+returnValue);
				returnValue = util.setFailureReponse(false, "uid is empay or null", "user");
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
			User user  = util.setJsonToObject(userData,User.class);
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

	@RequestMapping("*")
	@ResponseBody
	public String fallbackMethod(){
		return "fallback method";
	}

	@GetMapping(value="/Student/Basic/{studentid}" )
	@ResponseBody
	public String getGenders(@PathVariable("studentid") String studentid,@RequestHeader MultiValueMap<String, String> headers) {

		String returnValue = null;
		System.out.println(studentid);
		System.out.println(headers);
		return returnValue;  
	}
	@GetMapping(value="/app/nationalities" )
	@ResponseBody
	public String getNationality(@RequestHeader MultiValueMap<String, String> headers) {

		String returnValue = null;
		util
		System.out.println(new Utilities().getHeaders(headers,"/app/nationalities"));
		return returnValue;  
	}
	@GetMapping(value="/app/religions" )
	@ResponseBody
	public String getReligions(@RequestHeader MultiValueMap<String, String> headers) {

		String returnValue = null;
		System.out.println("--------------------");
		return returnValue;  
	}

	@GetMapping(value="/app/genders" )
	@ResponseBody
	public String getGenders(@RequestHeader MultiValueMap<String, String> headers) {

		String returnValue = null;
		System.out.println("--------------------"+headers);
		return returnValue;  
	}
	
	/*	@RequestMapping(value="/updateUser", method=RequestMethod.POST , headers = "Accept=application/json") 
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
			returnValue = "UserName : '"+user.getUserId()+"', Deleted Successfully";
			// deleteUser
		} catch (Exception e) {
			returnValue = "Exception : "+e.getMessage();
		}
		return returnValue;
	}
	 */


}