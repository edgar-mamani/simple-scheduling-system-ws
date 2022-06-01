package com.truextend.scheduling.controller;

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

import com.truextend.scheduling.entity.Student;
import com.truextend.scheduling.service.StudentService;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

	private StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Student createStudent(@RequestBody Student student) {
		return studentService.createStudent(student);
	}
	
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable("id") Integer studentId) {
		return studentService.getStudentById(studentId);
	}
	
	@GetMapping
	public Iterable<Student> getStudents() {
		return studentService.getAllStudents();
	}
	
	@PutMapping
	public void updateStudent(@RequestBody Student student) {
		studentService.updateStudent(student);
	}
	
	@DeleteMapping
	public void deleteStudent(@PathVariable Integer studentId) {
		studentService.deleteStudent(studentId);
	}
}
