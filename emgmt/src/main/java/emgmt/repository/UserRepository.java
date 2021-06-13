package emgmt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emgmt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User findByUID(String Uid);
	boolean isExistsByUID(String UId);

}