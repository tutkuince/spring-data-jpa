package com.incetutku.manytoone.client;

import com.incetutku.manytoone.service.CollegeManagementService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ManyToOneClient implements ApplicationRunner {

    private final CollegeManagementService collegeManagementService;

    public ManyToOneClient(CollegeManagementService collegeManagementService) {
        this.collegeManagementService = collegeManagementService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // persisting
//        collegeManagementService.persistingAStudentAlongWithItsAssociatedGuide();
//        collegeManagementService.persistingAStudentAlongWithItsAssociatedGuideFetchTypeMerge();

        // collegeManagementService.findingAStudentByItsId();

        collegeManagementService.persistingAGuideAlonWithItsAssociatedStudents();

        collegeManagementService.findingAGuideByItsId();

        collegeManagementService.updatingAStudent();
    }
}
