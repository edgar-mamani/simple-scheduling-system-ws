package com.truextend.scheduling.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truextend.scheduling.dto.EnrollmentDTO;
import com.truextend.scheduling.entity.Enrollment;
import com.truextend.scheduling.repository.EnrollmentRepository;
import com.truextend.scheduling.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Override
	public EnrollmentDTO enroll(EnrollmentDTO enrollmentDTO) {
		
		enrollmentRepository.save(new Enrollment(enrollmentDTO.getStudentId(), enrollmentDTO.getCourseCode()));

		return enrollmentDTO;
	}

}
