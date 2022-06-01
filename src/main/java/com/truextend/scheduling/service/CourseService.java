package com.truextend.scheduling.service;

import java.util.List;

import com.truextend.scheduling.entity.Course;

public interface CourseService {

	Course createCourse(Course course);
	
	Course getCourseByCode(Integer courseCode);

	List<Course> getAllCourses();

	void updateCourse(Course course);

	void deleteCourse(String courseCode);

}
