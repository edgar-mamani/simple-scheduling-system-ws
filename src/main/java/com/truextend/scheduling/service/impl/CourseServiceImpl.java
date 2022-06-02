package com.truextend.scheduling.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.truextend.scheduling.entity.Course;
import com.truextend.scheduling.exception.EntityNotFoundException;
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
		Optional<Course> optCourse = courseRepository.findById(courseCode);
		
		if (!optCourse.isPresent()) throw new EntityNotFoundException(Course.class, "classCode", courseCode);
		
		return optCourse.get();
	}

	@CachePut(value = "courses", key = "#course.code")
	@Override
	public void updateCourse(Course course) {
		getCourseByCode(course.getCode());
		
		courseRepository.save(course);
	}

	@CacheEvict(value = "courses", key = "#courseCode")
	@Override
	public void deleteCourse(String courseCode) {
		getCourseByCode(courseCode);
		
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
