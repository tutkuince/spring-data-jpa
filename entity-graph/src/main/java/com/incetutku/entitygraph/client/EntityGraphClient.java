package com.incetutku.entitygraph.client;

import com.incetutku.entitygraph.service.CollegeManagementService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class EntityGraphClient implements ApplicationRunner {

    private final CollegeManagementService service;

    public EntityGraphClient(CollegeManagementService service) {
        this.service = service;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // persisting
        service.persistingAGuideAlonWithItsAssociatedStudents();

        // loading Guide[id=2L] and its associated "students"
        service.foo();
    }
}
