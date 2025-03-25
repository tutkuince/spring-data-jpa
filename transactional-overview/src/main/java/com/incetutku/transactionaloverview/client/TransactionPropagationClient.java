package com.incetutku.transactionaloverview.client;

import com.incetutku.transactionaloverview.service.ItemService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// @Component
public class TransactionPropagationClient implements ApplicationRunner {

    private final ItemService itemService;

    public TransactionPropagationClient(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        itemService.persistAnItem();
    }
}
