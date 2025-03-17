package com.incetutku.derivedqueriesoverview.repository;

import com.incetutku.derivedqueriesoverview.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // Is, Equals
    User findByUsername(String username);

    User findByUsernameIs(String username);

    User findByUsernameEquals(String username);

    // OrderBy, Asc, Desc
    List<User> findAllByOrderByRegistrationDate();

    List<User> findAllByOrderByRegistrationDateAsc();

    List<User> findAllByOrderByRegistrationDateDesc();

    // Question 1
    // Find all by users by "registration_date" and order the result by their "username"
    List<User> findByRegistrationDateOrderByUsername(LocalDate registrationDate);

    List<User> findByRegistrationDateOrderByUsernameAsc(LocalDate registrationDate);

    // Between, Before, After
    List<User> findByRegistrationDateBetween(LocalDate start, LocalDate end);   // registrationDate between ?1 and ?2

    List<User> findByRegistrationDateBefore(LocalDate before);

    List<User> findByRegistrationDateAfter(LocalDate after);

    // In, NotIn
    List<User> findByLevelIn(Collection<Integer> levels);

    List<User> findByLevelNotIn(Collection<Integer> levels);

    // LessThan, LessThanEqual, GreaterThan, GreaterThanEqual
    List<User> findByLevelLessThan(Integer level);

    List<User> findByLevelLessThanEqual(Integer level);

    List<User> findByLevelGreaterThan(Integer level);

    List<User> findByLevelGreaterThanEqual(Integer level);

    // StartingWith, EndingWith
    List<User> findByUsernameStartingWith(String start);

    List<User> findByUsernameEndingWith(String end);

    // Containing, ContainingIgnoreCase
    List<User> findByUsernameContaining(String contains); // where username like %?1%

    List<User> findByUsernameContainingIgnoreCase(String contains); // where upper(u.username) like upper(%?1%)

    // IsNull, Null, IsNotNull, NotNull
    List<User> findByUsernameIsNull();

    List<User> findByUsernameNull();

    List<User> findByUsernameIsNotNull();

    List<User> findByUsernameNotNull();

    // IsNot, Not
    List<User> findByLevelIsNot(Integer level);

    List<User> findByLevelNot(Integer level);

    // Boolean
    List<User> findByIsActiveTrue();

    List<User> findByIsActiveFalse();

    // find first two "active users whose "username" ends with alphabet "e"
    List<User> findFirst2ByIsActiveAndUsernameEndingWith(Boolean isActive, String ending);

    List<User> findFirst2ByIsActiveTrueAndUsernameEndingWith(String ending);
}
