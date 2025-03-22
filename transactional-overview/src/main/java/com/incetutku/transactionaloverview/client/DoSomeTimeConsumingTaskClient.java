package com.incetutku.transactionaloverview.client;

import com.incetutku.transactionaloverview.entity.Ticket;
import com.incetutku.transactionaloverview.repository.TicketRepository;
import com.incetutku.transactionaloverview.service.TicketService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;

@Component
public class DoSomeTimeConsumingTaskClient implements ApplicationRunner {

    private final TicketService ticketService;
    private final TicketRepository ticketRepository;

    public DoSomeTimeConsumingTaskClient(TicketService ticketService, TicketRepository ticketRepository) {
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Populating DB
        Ticket ticket = new Ticket("Bus Stop 1", "Bus Stop 2", LocalDateTime.of(2025, Month.MARCH, 3, 14, 0));
        ticketRepository.save(ticket);

        ticketService.doSomeTimeConsumingTask();
    }
}
