package emgmt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emgmt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
 public User findByUid(String Uid);
 public boolean existsByEmailaddress(String emailaddress);
 public boolean existsByphonenumber(String phonenumber);
}