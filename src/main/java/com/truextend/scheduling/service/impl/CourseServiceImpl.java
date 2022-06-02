package com.truextend.scheduling.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.truextend.scheduling.dto.CourseDTO;
import com.truextend.scheduling.entity.Course;
import com.truextend.scheduling.exception.EntityNotFoundException;
import com.truextend.scheduling.repository.CourseRepository;
import com.truextend.scheduling.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public CourseDTO createCourse(CourseDTO courseDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Course course = modelMapper.map(courseDTO, Course.class);
		
		courseRepository.save(course);
		
		return courseDTO;
	}

	@Cacheable(value = "courses", key = "#courseCode")
	@Override
	public CourseDTO getCourseByCode(String courseCode) {
		Optional<Course> optCourse = courseRepository.findById(courseCode);
		
		if (!optCourse.isPresent()) throw new EntityNotFoundException(Course.class, "classCode", courseCode);
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(optCourse.get(), CourseDTO.class);
	}

	@CachePut(value = "courses", key = "#course.code")
	@Override
	public void updateCourse(CourseDTO courseDTO) {
		getCourseByCode(courseDTO.getCode());
		
		ModelMapper modelMapper = new ModelMapper();
		
		courseRepository.save(modelMapper.map(courseDTO, Course.class));
	}

	@CacheEvict(value = "courses", key = "#courseCode")
	@Override
	public void deleteCourse(String courseCode) {
		getCourseByCode(courseCode);
		
		courseRepository.deleteById(courseCode);
	}
	
	@Override
	public List<CourseDTO> getAllCourses() {
		Iterable<Course> courses = courseRepository.findAll();
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<CourseDTO> response = new ArrayList<>(0);
		
		courses.forEach(c -> response.add(modelMapper.map(c, CourseDTO.class)));
		
		return response;
	}

	@Override
	public List<CourseDTO> getCoursesByStudentId(Integer studentId) {
		List<Course> courses = courseRepository.findCoursesByStudentId(studentId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<CourseDTO> response = new ArrayList<>(0);
		
		courses.stream().forEach(c -> response.add(modelMapper.map(c, CourseDTO.class)));
		
		return response;
	}
}
