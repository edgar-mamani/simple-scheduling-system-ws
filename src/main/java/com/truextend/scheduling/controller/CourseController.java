package com.truextend.scheduling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.truextend.scheduling.entity.Course;
import com.truextend.scheduling.service.CourseService;

@RestController
@RequestMapping("/api/v1/classes")
public class CourseController {

	private CourseService courseService;
	
	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Course createCourse(@RequestBody Course course) {
		return courseService.createCourse(course);
	}
	
	@GetMapping("/{id}")
	public Course getCourseByCode(@PathVariable("id") String courseCode) {
		return courseService.getCourseByCode(courseCode);
	}
	
	@GetMapping
	public Iterable<Course> getCourses() {
		return courseService.getAllCourses();
	}
	
	@PutMapping
	public void updateCourse(@RequestBody Course course) {
		courseService.updateCourse(course);
	}
	
	@DeleteMapping
	public void deleteCourse(@PathVariable String courseCode) {
		courseService.deleteCourse(courseCode);
	}
	
	@GetMapping("/students/{studentId}")
	public List<Course> getStudentsByCourse(@PathVariable Integer studentId) {
		return courseService.getCoursesByStudentId(studentId);
	}
}
