package com.incetutku.transactionaloverview.client;

import com.incetutku.transactionaloverview.service.CollegeManagementService;
import org.springframework.stereotype.Component;

@Component
public class UserYClient implements Runnable {

    private final CollegeManagementService collegeManagementService;

    public UserYClient(CollegeManagementService collegeManagementService) {
        this.collegeManagementService = collegeManagementService;
    }

    @Override
    public void run() {
        collegeManagementService.raiseSalaryOfGuide(3L, 4000);
    }
}
