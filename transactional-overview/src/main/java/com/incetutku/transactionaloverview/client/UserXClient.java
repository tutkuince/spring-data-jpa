package com.incetutku.transactionaloverview.client;

import com.incetutku.transactionaloverview.entity.Guide;
import com.incetutku.transactionaloverview.service.CollegeManagementService;
import org.springframework.stereotype.Component;

@Component
public class UserXClient implements Runnable {

    private final CollegeManagementService collegeManagementService;

    public UserXClient(CollegeManagementService collegeManagementService) {
        this.collegeManagementService = collegeManagementService;
    }

    @Override
    public void run() {
        collegeManagementService.prepareNameAndSalaryReportOfAllGuides();
    }
}
