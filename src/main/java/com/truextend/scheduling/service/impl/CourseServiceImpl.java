package com.truextend.scheduling.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.truextend.scheduling.entity.Course;
import com.truextend.scheduling.repository.CourseRepository;
import com.truextend.scheduling.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	@Cacheable(value = "courses", key = "#courseCode")
	@Override
	public Course getCourseByCode(String courseCode) {
		return courseRepository.findById(courseCode).orElse(null);
	}

	@CachePut(value = "courses", key = "#course.code")
	@Override
	public void updateCourse(Course course) {
		courseRepository.save(course);
	}

	@CacheEvict(value = "courses", key = "#courseCode")
	@Override
	public void deleteCourse(String courseCode) {
		courseRepository.deleteById(courseCode);
	}
	
	@Override
	public Iterable<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public List<Course> getCoursesByStudentId(Integer studentId) {
		return courseRepository.findCoursesByStudentId(studentId);
	}
}
