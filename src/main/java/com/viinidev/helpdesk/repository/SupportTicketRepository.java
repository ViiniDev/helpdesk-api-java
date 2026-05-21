package com.viinidev.helpdesk.repository;

import com.viinidev.helpdesk.domain.SupportTicket;
import com.viinidev.helpdesk.domain.TicketPriority;
import com.viinidev.helpdesk.domain.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {

    List<SupportTicket> findByStatus(TicketStatus status);

    List<SupportTicket> findByPriority(TicketPriority priority);
}
