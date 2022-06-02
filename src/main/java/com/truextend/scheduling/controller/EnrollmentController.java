package com.truextend.scheduling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.truextend.scheduling.dto.EnrollmentDTO;
import com.truextend.scheduling.service.EnrollmentService;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {
	
	private EnrollmentService enrollmentService;
	
	@Autowired
	public EnrollmentController(EnrollmentService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EnrollmentDTO createCourse(@RequestBody EnrollmentDTO enrollment) {
		return enrollmentService.enroll(enrollment);
	}
}
