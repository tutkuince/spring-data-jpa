package com.incetutku.derivedqueriesoverview.repository;

import com.incetutku.derivedqueriesoverview.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEnrollmentId(String enrollmentId);
}
