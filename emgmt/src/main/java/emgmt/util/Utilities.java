package emgmt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import emgmt.common.exception;
import emgmt.common.response;
import emgmt.model.UserJson;

public class Utilities {

	public boolean isNullorWhiteSpaces(String value){
		boolean flag = true;
		try {
			if (value != null || !value.trim().equals("")) {
				flag = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}	
		return flag;
	}
	public UserJson setJsonToObject(String userData) {
		UserJson userJson = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		try {
			userJson = objectMapper.readValue(userData, UserJson.class);

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return userJson;
	}
	public String setObjectToJson(UserJson user) {
		String userJson = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		try {
			userJson = objectMapper.writeValueAsString(user);			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return userJson;
	}
	public String setObjectToJson(response response) {
		String responseJson = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		try {
			responseJson = objectMapper.writeValueAsString(response);			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return responseJson;
	}
	public String setFailureReponse(boolean success, String message) {
		response  res =  new response();
		res.setSuccess(success);
		exception exc =new exception();
		exc.setMessage(message);
		res.setException(exc);

		return setObjectToJson(res);

	}
	public String setSucessReponse(boolean success,String data) {
		response  res =  new response();
		res.setSuccess(success);
		res.setData(data);
		return setObjectToJson(res);

	}
	public String setFailureReponse(boolean success, Exception e) {
		response  res =  new response();
		res.setSuccess(success);
		res.setException(setErrorDetails(e));
		return setObjectToJson(res);

	}

	private exception setErrorDetails(final Throwable cause)
	{
		Throwable rootCause = cause;
		while(rootCause.getCause() != null &&  rootCause.getCause() != rootCause)
			rootCause = rootCause.getCause();

		exception exc =new exception();
		exc.setMessage(cause.getMessage());
		exc.setLinno(rootCause.getStackTrace()[0].getLineNumber());
		exc.setMethod(rootCause.getStackTrace()[0].getClassName()+"."+rootCause.getStackTrace()[0].getMethodName());

		return exc;
	}
}
