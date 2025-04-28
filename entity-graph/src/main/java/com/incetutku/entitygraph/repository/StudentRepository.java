package com.incetutku.entitygraph.repository;

import com.incetutku.entitygraph.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
