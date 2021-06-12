package emgmt.service;


import java.util.List;

import emgmt.exception.RecordNotFoundException;
import emgmt.model.User;

public interface IUserService {

    List<User> findAll();
    public User getUserDetailsById(String UserId)throws RecordNotFoundException;
    public void deleteUser(String userID) throws RecordNotFoundException;
	public String createUser(User userDetail);
	public String updateUser(User userDetail);
	public  String checkLoginCredentials(User credentialUI , User retrieved4mDB);
	public List<User> getAllUsers();
	  
    
}