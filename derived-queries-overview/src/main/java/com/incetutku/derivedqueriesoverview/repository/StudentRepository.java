package com.incetutku.derivedqueriesoverview.repository;

import com.incetutku.derivedqueriesoverview.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEnrollmentId(String enrollmentId);

    List<Student> findByEnrollmentIdStartingWithAndNameLike(String enrollmentId, String name);

    List<Student> findFirst2ByEnrollmentIdStartingWithAndNameLike(String enrollmentId, String name);    // LIMIT with First 2

    @Query("SELECT s FROM Student as s WHERE s.name = :name")
    List<Student> findByName(String name);

    @Query(value = "SELECT * FROM Student WHERE Name LIKE %?", nativeQuery = true)
    List<Student> findByNameEndingWith(String name);

    // Order Of Execution: @Query (JPQL) -> @NamedQuery -> Derived Query
}
