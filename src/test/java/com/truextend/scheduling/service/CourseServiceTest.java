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

import com.truextend.scheduling.dto.CourseDTO;
import com.truextend.scheduling.entity.Course;
import com.truextend.scheduling.exception.EntityNotFoundException;
import com.truextend.scheduling.repository.CourseRepository;
import com.truextend.scheduling.service.impl.CourseServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

	@Mock
	private CourseRepository courseRepository;
	
	@InjectMocks
	private CourseServiceImpl courseService;
	
	@Test
	public void givenValidCourseCode_whenSearch_shouldReturnCourse() {
		
		String validCourseCode = "cs-123";
		
		var courseDTO = new CourseDTO();
		courseDTO.setCode(validCourseCode);
		
		var course = new Course();
		course.setCode(validCourseCode);
		
		given(courseRepository.findById(validCourseCode)).willReturn(Optional.of(course));
		
		var result = courseService.getCourseByCode(validCourseCode);
		
		assertEquals(result.getCode(), courseDTO.getCode());
	}
	
	@Test
	public void givenNotValidCourseCode_whenSearch_shouldThrowException() {
		
		String noValidCourseCode = "cs-123";
		
		given(courseRepository.findById(noValidCourseCode)).willReturn(Optional.empty());
		
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			courseService.getCourseByCode(noValidCourseCode);
		});
	}
}
