package com.incetutku.transactionaloverview.service;

import com.incetutku.transactionaloverview.entity.Guide;
import com.incetutku.transactionaloverview.repository.GuideRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Guide guide = guideRepository.findById(1L).get();
        guide.setSalary(2500);
    }

    @Transactional(readOnly = true)
    public void fetchingReadOnlyGuide() {
        Guide guide = guideRepository.findById(1L).get();
        guide.setSalary(2500);
    }
}
