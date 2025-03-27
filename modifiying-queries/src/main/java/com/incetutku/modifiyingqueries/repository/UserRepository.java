package com.incetutku.modifiyingqueries.repository;

import com.incetutku.modifiyingqueries.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

//    @Transactional    // derived queries delete the data one by one. it is not a bulk operations.
//    Integer deleteByLevel(Integer level); // bulk operations run a bit faster.

    @Transactional
    @Modifying
    @Query("DELETE FROM User as u WHERE u.level =: level")
    Integer deleteInBulByLevel(Integer level);
}
