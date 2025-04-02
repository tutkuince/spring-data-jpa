package com.incetutku.modifiyingqueries.client;

import com.incetutku.modifiyingqueries.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ModifyingClient implements ApplicationRunner {

    private final UserService userService;

    public ModifyingClient(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // populate db
        userService.persistUsers();

        // deleting users one-by-one by level
        // userService.deleteUsersByLevel();

        // deleting users in-bulk by level
        // userService.deleteUsersInBulkByLevel();

        userService.updateUser();
    }
}
