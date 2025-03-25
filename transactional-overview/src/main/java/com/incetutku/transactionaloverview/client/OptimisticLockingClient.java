package com.incetutku.transactionaloverview.client;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Order(2)
public class OptimisticLockingClient implements ApplicationRunner {

    private final User1Client user1Client;
    private final User2Client user2Client;

    public OptimisticLockingClient(User1Client user1Client, User2Client user2Client) {
        this.user1Client = user1Client;
        this.user2Client = user2Client;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(user1Client);
        service.execute(user2Client);

        service.shutdown();
    }
}
