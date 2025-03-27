package com.incetutku.modifiyingqueries.repository;

import com.incetutku.modifiyingqueries.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional    // derived queries delete the data one by one. it is not a bulk operations.
    Integer deleteByLevel(Integer level); // bulk operations run a bit faster.

    @Transactional  // by default, there is no transactional annotation with using modifying annotation
    @Modifying
    @Query("DELETE FROM User as u WHERE u.level = :level")
    Integer deleteInBulkByLevel(Integer level);
    // Please make sure that the modifying query that you want to run to perform your bulk operation is being run within a transaction.
    // otherwise it will throw an exception.
}
