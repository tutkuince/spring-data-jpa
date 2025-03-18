package com.incetutku.derivedqueriesoverview.repository;

import com.incetutku.derivedqueriesoverview.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // Question - 2
    // find first two "active users whose "username" ends with alphabet "e"
    List<User> findFirst2ByIsActiveAndUsernameEndingWith(Boolean isActive, String ending);

    List<User> findFirst2ByIsActiveTrueAndUsernameEndingWith(String ending);

    // Task - 1: Find all users with their level ordered in descending order
    List<User> findByOrderByLevelDesc();

    // Task - 2: Find just 2 users with their level ordered in descending order
    List<User> findFirst2ByOrderByLevelDesc();

    List<User> findTop2ByOrderByLevelDesc();

    // Task - 3: Find just first user from users ordered by their level in descending order
    User findFirstByOrderByLevelDesc();
    User findTOpByOrderByLevelDesc();

    // Task - 4: Find all users who are either inactive or 1st level
    List<User> findByIsActiveTrueOrLevel(Integer level);
    List<User> findByIsActiveOrLevel(Boolean isActive, Integer level);

    // Task - 5: Find all users whose email contains the string "else" in it
    List<User> findByEmailContaining(String containing);

    Page<User> findAll(Pageable pageable); // userRepository.findAll(PageRequest.of(0,3)); // first parameter is page number, second one is size of the data
}
