package com.incetutku.transactionaloverview.repository;

import com.incetutku.transactionaloverview.entity.Guide;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuideRepository extends JpaRepository<Guide, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT g.name, g.salary FROM Guide as g")
    List<Object[]> getNameAndSalaryOfAll();

    @Query("SELECT SUM(g.salary) FROM Guide as g")
    Long calculateSumOfAllSalaries();
}
