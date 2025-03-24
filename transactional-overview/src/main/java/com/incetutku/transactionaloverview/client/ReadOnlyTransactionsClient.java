package com.incetutku.transactionaloverview.client;

import com.incetutku.transactionaloverview.service.CollegeManagementService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// @Component
public class ReadOnlyTransactionsClient implements ApplicationRunner {

    private final CollegeManagementService collegeManagementService;

    public ReadOnlyTransactionsClient(CollegeManagementService collegeManagementService) {
        this.collegeManagementService = collegeManagementService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // pre populating db
        collegeManagementService.persistingSomeGuides();

        // raising salary to 2050 using READ_WRITE transaction
        collegeManagementService.fetchingReadWriteGuide();

        // raising salary to 2500 using READ_ONLY transaction
        // collegeManagementService.fetchingReadOnlyGuide();
    }
}
