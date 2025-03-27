package com.incetutku.modifiyingqueries.repository;

import com.incetutku.modifiyingqueries.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
