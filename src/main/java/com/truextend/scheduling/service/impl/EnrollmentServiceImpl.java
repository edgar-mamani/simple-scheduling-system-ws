package com.truextend.scheduling.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truextend.scheduling.dto.EnrollmentDTO;
import com.truextend.scheduling.entity.Course;
import com.truextend.scheduling.entity.Enrollment;
import com.truextend.scheduling.entity.Student;
import com.truextend.scheduling.exception.EntityNotFoundException;
import com.truextend.scheduling.repository.CourseRepository;
import com.truextend.scheduling.repository.EnrollmentRepository;
import com.truextend.scheduling.repository.StudentRepository;
import com.truextend.scheduling.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public EnrollmentDTO enroll(EnrollmentDTO enrollmentDTO) {
		Optional<Student> optStudent = studentRepository.findById(enrollmentDTO.getStudentId());
		
		if (!optStudent.isPresent()) throw new EntityNotFoundException(Student.class, "studentId", enrollmentDTO.getStudentId().toString());
		
		Optional<Course> optCourse = courseRepository.findById(enrollmentDTO.getCourseCode());
		
		if (!optCourse.isPresent()) throw new EntityNotFoundException(Course.class, "classCode", enrollmentDTO.getCourseCode());
		
		enrollmentRepository.save(new Enrollment(enrollmentDTO.getStudentId(), enrollmentDTO.getCourseCode()));

		return enrollmentDTO;
	}

}
