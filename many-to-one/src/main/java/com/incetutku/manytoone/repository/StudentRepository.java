package com.incetutku.manytoone.repository;

import com.incetutku.manytoone.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
