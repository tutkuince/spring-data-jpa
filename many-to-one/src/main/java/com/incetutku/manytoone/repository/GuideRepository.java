package com.incetutku.manytoone.repository;

import com.incetutku.manytoone.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideRepository extends JpaRepository<Guide, Long> {
}
