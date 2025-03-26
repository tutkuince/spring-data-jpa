package com.incetutku.transactionaloverview.client;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Order(2)
public class PessimisticLockingClient implements ApplicationRunner {

    private final UserXClient userXClient;
    private final UserYClient userYClient;

    public PessimisticLockingClient(UserXClient userXClient, UserYClient userYClient) {
        this.userXClient = userXClient;
        this.userYClient = userYClient;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(userYClient);
        service.execute(userXClient);

        service.shutdown();
    }
}
