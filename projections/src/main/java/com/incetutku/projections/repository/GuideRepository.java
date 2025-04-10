package com.incetutku.projections.repository;

import com.incetutku.projections.entity.Guide;
import com.incetutku.projections.projection.GuideNameSalary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuideRepository extends JpaRepository<Guide, Long> {

    //    List<Guide> findFirst3BySalaryGreaterThan(Integer salary);
    List<GuideNameSalary> findFirst3BySalaryGreaterThan(Integer salary);
}
