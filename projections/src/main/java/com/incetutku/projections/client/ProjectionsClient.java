package com.incetutku.projections.client;

import com.incetutku.projections.service.CollegeManagementService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ProjectionsClient implements ApplicationRunner {

    private final CollegeManagementService collegeManagementService;

    public ProjectionsClient(CollegeManagementService collegeManagementService) {
        this.collegeManagementService = collegeManagementService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // pre-populating data
        collegeManagementService.populateData();

        // displaying "name" and "salary" data of first 3 guides whose salary is > 2000
        collegeManagementService.displayNameAndSalaryOfFirst3GuidesBySalaryGreaterThan2000();
    }
}
