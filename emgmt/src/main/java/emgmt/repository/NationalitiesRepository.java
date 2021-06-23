package emgmt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emgmt.model.Nationalities;

@Repository
public interface NationalitiesRepository extends JpaRepository<Nationalities, String> {
}