package com.truextend.scheduling.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.truextend.scheduling.entity.Enrollment;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment, Enrollment.Id> {

	@Query("select e from Enrollment e where e.id.studentId = ?1 and e.id.courseCode = ?2")
	Optional<Enrollment> checkAvailabilityByStudentIdAndCourseCode(Integer studentId, String courseCode);
}
