package com.incetutku.projections.repository;

import com.incetutku.projections.entity.Guide;
import com.incetutku.projections.projection.GuideNameSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuideRepository extends JpaRepository<Guide, Long> {

    //    List<Guide> findFirst3BySalaryGreaterThan(Integer salary);
    // List<GuideNameSalary> findFirst3BySalaryGreaterThan(Integer salary);

//    @Query(value = "SELECT g.name, g.salary FROM Guide as g WHERE g.salary > :salary LIMIT 3", nativeQuery = true)
    // we can use it with @Value annotations to avoid load unnecessary data attributes
    List<GuideNameSalary> findFirst3BySalaryGreaterThan(Integer salary);
}
