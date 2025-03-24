package com.incetutku.transactionaloverview.repository;

import com.incetutku.transactionaloverview.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
