package com.truextend.scheduling.service;

import com.truextend.scheduling.entity.Course;

public interface CourseService {

	Course createCourse(Course course);
	
	Course getCourseByCode(String courseCode);

	Iterable<Course> getAllCourses();

	void updateCourse(Course course);

	void deleteCourse(String courseCode);

}
