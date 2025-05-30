package com.incetutku.transactionaloverview.client;

import com.incetutku.transactionaloverview.entity.Guide;
import com.incetutku.transactionaloverview.service.CollegeManagementService;
import org.springframework.stereotype.Component;

@Component
public class User1Client implements Runnable {

    private final CollegeManagementService collegeManagementService;

    public User1Client(CollegeManagementService collegeManagementService) {
        this.collegeManagementService = collegeManagementService;
    }

    @Override
    public void run() {
        Guide guide = collegeManagementService.findGuideById(2L);
        guide.setSalary(3000);
        collegeManagementService.updateGuide(guide);
    }
}
