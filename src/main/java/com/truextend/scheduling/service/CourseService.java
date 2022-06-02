package com.truextend.scheduling.service;

import java.util.List;

import com.truextend.scheduling.dto.CourseDTO;

public interface CourseService {

	CourseDTO createCourse(CourseDTO courseDTO);
	
	CourseDTO getCourseByCode(String courseCode);

	List<CourseDTO> getAllCourses();

	void updateCourse(CourseDTO courseDTO);

	void deleteCourse(String courseCode);

	List<CourseDTO> getCoursesByStudentId(Integer studentId);

}
