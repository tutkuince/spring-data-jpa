package com.incetutku.transactionaloverview.client;

import com.incetutku.transactionaloverview.entity.Ticket;
import com.incetutku.transactionaloverview.service.TicketService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;

@Component
public class BookTicketClient implements ApplicationRunner {

    private final TicketService ticketService;

    public BookTicketClient(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Ticket ticket = new Ticket("Bus Stop 1", "Bust Stop 2", LocalDateTime.of(2025, Month.MARCH, 3, 14, 0));
        ticketService.bookTicket(ticket);
        System.out.println();
    }
}
