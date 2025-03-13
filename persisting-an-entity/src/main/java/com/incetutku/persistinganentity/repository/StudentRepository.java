package com.incetutku.persistinganentity.repository;

import com.incetutku.persistinganentity.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
