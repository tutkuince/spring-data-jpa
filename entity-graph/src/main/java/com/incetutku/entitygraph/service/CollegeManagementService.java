package com.incetutku.entitygraph.service;

import com.incetutku.entitygraph.entity.Guide;
import com.incetutku.entitygraph.entity.Student;
import com.incetutku.entitygraph.repository.GuideRepository;
import com.incetutku.entitygraph.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollegeManagementService {

    private final GuideRepository guideRepository;
    private final StudentRepository studentRepository;

    public CollegeManagementService(GuideRepository guideRepository, StudentRepository studentRepository) {
        this.guideRepository = guideRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional(readOnly = true)
    public void foo() {
        Guide guide = guideRepository.findById(2L).orElseThrow(RuntimeException::new);
        System.out.println(guide);
    }

    @Transactional
    public void persistingAGuideAlonWithItsAssociatedStudents() {
        Guide guide1 = new Guide("2000MO10789", "Mike Lawson", 1000);
        Guide guide2 = new Guide("2000IM10901", "Ian Lamb", 2000);

        Student student1 = new Student("2014JT50123", "John Smith", guide1);
        Student student2 = new Student("2014AL50456", "Amy Gill", guide2);

        guide1.getStudents().add(student1);
        guide1.getStudents().add(student2);

        guideRepository.save(guide1);
        guideRepository.save(guide2);
    }
}
