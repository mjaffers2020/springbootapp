package emgmt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emgmt.model.ProfileBasic;

@Repository
public interface ProfileBasicRepository extends JpaRepository<ProfileBasic, Integer> {
}