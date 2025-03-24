package com.incetutku.transactionaloverview.repository;

import com.incetutku.transactionaloverview.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideRepository extends JpaRepository<Guide, Long> {
}
