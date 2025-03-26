package com.incetutku.transactionaloverview.service;

import com.incetutku.transactionaloverview.entity.Guide;
import com.incetutku.transactionaloverview.repository.GuideRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CollegeManagementService {

    private final GuideRepository guideRepository;

    public CollegeManagementService(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    @Transactional
    public void persistingSomeGuides() {
        Guide g1 = new Guide("2000MO10789", "Mike Lawson", 1000);
        Guide g2 = new Guide("2000IM10901", "Ian Lamb", 2000);

        guideRepository.save(g1);
        guideRepository.save(g2);
    }

    @Transactional
    public void fetchingReadWriteGuide() {
        Guide guide = guideRepository.findById(1L).get(); // Snapshot copy
        guide.setSalary(2500);
    }   // Automatic Dirty Checking: After this method Guide Salary will be 2500.

    @Transactional(readOnly = true)
    public void fetchingReadOnlyGuide() {
        Guide guide = guideRepository.findById(1L).get();
        guide.setSalary(2500);
    }   // No Automatic Dirty Checking and No Flushing

    @Transactional(readOnly = true)
    public Guide findGuideById(Long id) {
        return guideRepository.findById(id).get();
    }

    @Transactional
    public void updateGuide(Guide guide) {
        guideRepository.save(guide);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void prepareNameAndSalaryReportOfAllGuides() {
        List<Object[]> resultList = guideRepository.getNameAndSalaryOfAll();
        resultList.forEach(result -> {
            System.out.println("Name: " + result[0] + "\t\tSalary: " + result[1]);
        });

        System.out.println(guideRepository.calculateSumOfAllSalaries());
    }

    @Transactional
    public void raiseSalaryOfGuide(Long id, Integer newSalary) {
        Guide guide = guideRepository.findById(id).get();
        guide.setSalary(newSalary);
    }
}
