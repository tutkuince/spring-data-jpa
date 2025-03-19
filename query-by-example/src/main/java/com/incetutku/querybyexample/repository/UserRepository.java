package com.incetutku.querybyexample.repository;

import com.incetutku.querybyexample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Query By Example (QBE) Limitations:
    // Only supports starts/contains/ends/regex matching for strings and exact matching for other property types
    // No support for nested/grouped property constraints, such as "firstname = ?0 or (firstname  = ?1 and lastname = ?2)"
}
