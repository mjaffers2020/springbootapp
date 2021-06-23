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

import emgmt.common.Constants;
import emgmt.model.Student;
import emgmt.model.User;
import emgmt.responsepacket.StudentBasicProfileResponse;
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
			returnValue = util.setFailureReponse(false, "uid is empay or null", "user");
		}

		return returnValue;  
	}

	@RequestMapping(value="/register", method=RequestMethod.POST , headers = "Accept=application/json") 
	@ResponseBody
	public String createNewUser(@RequestBody String userData){
		String returnValue = "Error";
		Utilities  util =  new Utilities();
		try {

			User user  = util.setJsonToObject(userData,User.class);
			returnValue = userService.createUser(user);
			if("Error".equalsIgnoreCase(returnValue)) {
				returnValue = util.setFailureReponse(false, "User Email Already Exits", "user");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = util.setFailureReponse(false, "User Email Already Exits", "user");
		}
		return returnValue;
	}

	@RequestMapping("*")
	@ResponseBody
	public String fallbackMethod(){
		return "fallback method";
	}

	@GetMapping(value= {Constants.STUDENT_BASIC_PROFILE, Constants.STUDENT_BASIC_PROFILE2} )
	@ResponseBody
	public String createNewStudent(@PathVariable("studentid") String studentid,@RequestHeader MultiValueMap<String, String> headers) {

		String returnValue = "Error";
		Utilities  util =  new Utilities();
		try {

			Student student  = util.setJsonToObject(studentid,Student.class);
			returnValue = userService.createStudent(student);
			if("Error".equalsIgnoreCase(returnValue)) {
				returnValue = util.setFailureReponse(false, "student is already exists", "user");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = util.setFailureReponse(false, "Exception While inserting Student Details", "user");
		}
		return returnValue;  
	}
	
	@GetMapping(value=Constants.NATIONALITIES )
	@ResponseBody
	public String getNationality(@RequestHeader MultiValueMap<String, String> headers) {

		String returnValue = null;
		Utilities util  =  new Utilities();
		try {
			List<String> routePaths = util.getHeaders(headers,Constants.NATIONALITIES);
			StudentBasicProfileResponse  studentBasicProfileResponse =  userService.validateRoutePaths(routePaths);
			returnValue = String.valueOf(util.setObjectToJson(studentBasicProfileResponse));
		}catch(Exception e) {
			returnValue = util.setFailureReponse(false, "Nationality path is undefined", "user");
			e.printStackTrace();
		}
		return returnValue;  
	}
	@GetMapping(value=Constants.RELIGIONS )
	@ResponseBody
	public String getReligions(@RequestHeader MultiValueMap<String, String> headers) {

		String returnValue = null;
		Utilities util  =  new Utilities();
		try {
			List<String> routePaths = util.getHeaders(headers,Constants.RELIGIONS);
			StudentBasicProfileResponse  studentBasicProfileResponse =  userService.validateRoutePaths(routePaths);
			returnValue = String.valueOf(util.setObjectToJson(studentBasicProfileResponse));
		}catch(Exception e) {
			returnValue = util.setFailureReponse(false, "Religion path is undefined", "user");
			e.printStackTrace();
		}
		return returnValue;  
	}

	@GetMapping(value=Constants.GENDERS )
	@ResponseBody
	public String getGenders(@RequestHeader MultiValueMap<String, String> headers) {

		String returnValue = null;
		Utilities util  =  new Utilities();
		try {
			List<String> routePaths = util.getHeaders(headers,Constants.GENDERS);
			StudentBasicProfileResponse  studentBasicProfileResponse =  userService.validateRoutePaths(routePaths);
			returnValue = String.valueOf(util.setObjectToJson(studentBasicProfileResponse));
		}catch(Exception e) {
			returnValue = util.setFailureReponse(false, "Genders path is undefined", "user");
			e.printStackTrace();
		}
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