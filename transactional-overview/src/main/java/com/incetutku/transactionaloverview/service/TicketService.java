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

    @Transactional
    public void bookTicket(Ticket ticket) {
        // begin transaction
        ticketRepository.save(ticket);
        throw new RuntimeException("Payment failed");
    }   // commit
}
