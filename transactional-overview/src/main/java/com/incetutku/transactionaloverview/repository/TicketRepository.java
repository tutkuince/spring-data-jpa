package com.incetutku.transactionaloverview.repository;

import com.incetutku.transactionaloverview.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
