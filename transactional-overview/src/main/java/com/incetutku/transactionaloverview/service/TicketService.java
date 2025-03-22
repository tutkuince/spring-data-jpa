package com.incetutku.transactionaloverview.service;

import com.incetutku.transactionaloverview.entity.Ticket;
import com.incetutku.transactionaloverview.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // @Transactional Overview
    // A transaction is a group of operations that are run as a single unit of work

    @Transactional
    public void bookTicket(Ticket ticket) {
        // begin transaction
        ticketRepository.save(ticket);
        throw new RuntimeException("Payment failed");
    }   // commit

    @Transactional
    public void doSomeTimeConsumingTask() throws InterruptedException {
        Thread.sleep(40000);

        Ticket ticket = ticketRepository.findById(1L).get();

        Thread.sleep(40000);
    }
}
