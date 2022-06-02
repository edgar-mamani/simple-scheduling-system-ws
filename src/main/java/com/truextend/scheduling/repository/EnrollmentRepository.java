package com.truextend.scheduling.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.truextend.scheduling.entity.Enrollment;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment, Enrollment.Id> {

}
