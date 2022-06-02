package com.truextend.scheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.truextend.scheduling.entity.Course;

public interface CourseRepository extends CrudRepository<Course, String> {

	@Query("select c from Course c join Enrollment e on e.id.courseCode = c.code where e.id.studentId = ?1")
	List<Course> findCoursesByStudentId(Integer studentId);
	
}
