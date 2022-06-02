package com.truextend.scheduling.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.truextend.scheduling.dto.CourseDTO;
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
	public CourseDTO createCourse(@Valid @RequestBody CourseDTO course) {
		return courseService.createCourse(course);
	}
	
	@GetMapping("/{id}")
	public CourseDTO getCourseByCode(@PathVariable("id") String courseCode) {
		return courseService.getCourseByCode(courseCode);
	}
	
	@GetMapping
	public List<CourseDTO> getCourses() {
		return courseService.getAllCourses();
	}
	
	@PutMapping
	public void updateCourse(@Valid @RequestBody CourseDTO courseDTO) {
		courseService.updateCourse(courseDTO);
	}
	
	@DeleteMapping
	public void deleteCourse(@PathVariable String courseCode) {
		courseService.deleteCourse(courseCode);
	}
	
	@GetMapping("/students/{studentId}")
	public List<CourseDTO> getStudentsByCourse(@PathVariable Integer studentId) {
		return courseService.getCoursesByStudentId(studentId);
	}
}
