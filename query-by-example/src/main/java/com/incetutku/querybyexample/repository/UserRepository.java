package com.incetutku.querybyexample.repository;

import com.incetutku.querybyexample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
