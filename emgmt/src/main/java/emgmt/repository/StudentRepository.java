package emgmt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emgmt.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	public Student findByStudentId(int studentId);
	public boolean existsByStudentId(int studentId);
}