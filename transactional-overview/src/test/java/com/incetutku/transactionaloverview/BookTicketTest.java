package com.incetutku.transactionaloverview;

import com.incetutku.transactionaloverview.entity.Ticket;
import com.incetutku.transactionaloverview.repository.TicketRepository;
import com.incetutku.transactionaloverview.service.TicketService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookTicketTest {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void testBookingATicket() {
        try {
            Ticket ticket = new Ticket("Bus Stop 1", "Bust Stop 2", LocalDateTime.of(2025, Month.MARCH, 3, 14, 0));
            ticketService.bookTicket(ticket);
        } catch (Exception e) {
            System.out.println("===>" + e.getMessage());
        } finally {
            assertThat(ticketRepository.findAll()).isEmpty();
        }
    }
}
