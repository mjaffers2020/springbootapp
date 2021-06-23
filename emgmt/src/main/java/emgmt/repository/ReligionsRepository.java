package emgmt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emgmt.model.Religions;

@Repository
public interface ReligionsRepository extends JpaRepository<Religions, String> {
}