package com.truextend.scheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.truextend.scheduling.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

	@Query("select s from Student s join Enrollment e on e.id.studentId = s.id where e.id.courseCode = ?1")
	public List<Student> findStudentsByCourse(String courseCode);
}
