package com.incetutku.derivedqueriesoverview.repository;

import com.incetutku.derivedqueriesoverview.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEnrollmentId(String enrollmentId);

    List<Student> findByEnrollmentIdStartingWithAndNameLike(String enrollmentId, String name);

    List<Student> findFirst2ByEnrollmentIdStartingWithAndNameLike(String enrollmentId, String name);    // LIMIT with First 2
}
