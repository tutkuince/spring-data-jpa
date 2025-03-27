package com.incetutku.modifiyingqueries.service;

import com.incetutku.modifiyingqueries.entity.User;
import com.incetutku.modifiyingqueries.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void persistUsers() {
        User u1 = new User("John", LocalDate.of(2021, Month.AUGUST, 4), "john@email.com", 1, true);
        User u2 = new User("Jane", LocalDate.of(2019, Month.MARCH, 18), "jane@email.com", 2, true);
        User u3 = new User("Nicole", LocalDate.of(2017, Month.JULY, 21), "nicole@email.com", 1, false);
        User u4 = new User("Ravi", LocalDate.of(2018, Month.JUNE, 15), "ravi@email.com", 1, false);
        User u5 = new User("Alissa", LocalDate.of(2014, Month.APRIL, 5), "alissa@email.com", 2, true);

        userRepository.saveAll(List.of(u1, u2, u3, u4, u5));
    }

    @Transactional
    public void deleteUsersByLevel() {
        Integer countOfDeletedUsers = userRepository.deleteByLevel(1);
        System.out.println(countOfDeletedUsers);
    }

    @Transactional
    public void deleteUsersInBulkByLevel() {
        Integer countOfDeletedUsersInBulk = userRepository.deleteInBulkByLevel(2);
        System.out.println(countOfDeletedUsersInBulk);
    }
}
