package com.incetutku.entitygraph.repository;

import com.incetutku.entitygraph.entity.Guide;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuideRepository extends JpaRepository<Guide, Long> {
//    @EntityGraph(value = "Guide.students", type = EntityGraph.EntityGraphType.LOAD)
//    @EntityGraph(attributePaths = {"students"}, type = EntityGraph.EntityGraphType.LOAD)
    @EntityGraph(attributePaths = {"students.hostel"}, type = EntityGraph.EntityGraphType.LOAD) // entity sub-graph
    Optional<Guide> findById(Long id);
}
