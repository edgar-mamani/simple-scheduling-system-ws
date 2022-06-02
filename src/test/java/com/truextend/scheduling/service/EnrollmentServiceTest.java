package com.truextend.scheduling.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.truextend.scheduling.dto.EnrollmentDTO;
import com.truextend.scheduling.entity.Course;
import com.truextend.scheduling.entity.Student;
import com.truextend.scheduling.exception.EntityNotFoundException;
import com.truextend.scheduling.repository.CourseRepository;
import com.truextend.scheduling.repository.EnrollmentRepository;
import com.truextend.scheduling.repository.StudentRepository;
import com.truextend.scheduling.service.impl.EnrollmentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

	@Mock
	private EnrollmentRepository enrollmentRepository;
	
	@Mock
	private StudentRepository studentRepository;
	
	@Mock
	private CourseRepository courseRepository;
	
	@InjectMocks
	private EnrollmentServiceImpl enrollmentService;
	
	@Test
	public void givenValidsStudentIdAndCourseCode_whenEnroll_shouldReturnRegistration() {
		EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
		enrollmentDTO.setStudentId(1);
		enrollmentDTO.setCourseCode("CS-1234");
		
		given(studentRepository.findById(enrollmentDTO.getStudentId())).willReturn(Optional.of(new Student()));
		given(courseRepository.findById(enrollmentDTO.getCourseCode())).willReturn(Optional.of(new Course()));
		
		var result = enrollmentService.enroll(enrollmentDTO);
		
		assertEquals(result, enrollmentDTO);
	}
	
	@Test
	public void givenNotValidStudentIdAndCourseCode_whenTryToEnroll_shouldThrowException() {
		EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
		enrollmentDTO.setStudentId(1);
		enrollmentDTO.setCourseCode("CS-1234");
		
		given(studentRepository
				.findById(enrollmentDTO.getStudentId()))
				.willReturn(Optional.empty());
		
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			enrollmentService.enroll(enrollmentDTO);
		});
	}
}























