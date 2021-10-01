package emgmt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emgmt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
 public User findByuId(String uId);
 public boolean existsByEmailAddress(String emailAddress);
 public boolean existsByphoneNumber(String phoneNumber);
 public boolean existsByuId(String uId);
}