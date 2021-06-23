package emgmt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emgmt.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
	public Student findByStudentId(String studentId);
	public boolean existsByStudentId(String studentId);
}