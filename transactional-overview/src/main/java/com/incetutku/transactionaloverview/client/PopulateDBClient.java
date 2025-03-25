package com.incetutku.transactionaloverview.client;

import com.incetutku.transactionaloverview.entity.Guide;
import com.incetutku.transactionaloverview.repository.GuideRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class PopulateDBClient implements ApplicationRunner {

    private final GuideRepository guideRepository;

    public PopulateDBClient(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // pre-populating db with 3 guides
        Guide g1 = new Guide("2000MO10789", "Mike Lawson", 1000);
        Guide g2 = new Guide("2000IM10901", "Ian Lamb", 2500);
        Guide g3 = new Guide("2000DO10777", "David Crow", 3000);

        guideRepository.save(g1);
        guideRepository.save(g2);
        guideRepository.save(g3);
    }
}
