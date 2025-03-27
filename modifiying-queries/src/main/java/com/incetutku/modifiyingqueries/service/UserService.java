package com.incetutku.modifiyingqueries.service;

import com.incetutku.modifiyingqueries.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
