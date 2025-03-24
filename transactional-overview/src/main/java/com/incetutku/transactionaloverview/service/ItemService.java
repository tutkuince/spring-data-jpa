package com.incetutku.transactionaloverview.service;

import com.incetutku.transactionaloverview.entity.Item;
import com.incetutku.transactionaloverview.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final LoggingService loggingService;

    public ItemService(ItemRepository itemRepository, LoggingService loggingService) {
        this.itemRepository = itemRepository;
        this.loggingService = loggingService;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void persistAnItem() {
        Item item = new Item("Item-1", LocalDate.now(), 29);
        itemRepository.save(item);

        loggingService.logAMessage("Adding item with name: " + item.getName());
    }
}
