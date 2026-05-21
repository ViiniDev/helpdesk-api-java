package com.viinidev.helpdesk.service;

import com.viinidev.helpdesk.domain.SupportTicket;
import com.viinidev.helpdesk.domain.TicketPriority;
import com.viinidev.helpdesk.domain.TicketStatus;
import com.viinidev.helpdesk.dto.*;
import com.viinidev.helpdesk.repository.SupportTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {

    private final SupportTicketRepository ticketRepository;

    public TicketService(SupportTicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketResponse create(TicketRequest request) {
        SupportTicket ticket = new SupportTicket(
                request.requesterName(),
                request.requesterEmail(),
                request.title(),
                request.description(),
                request.priority()
        );
        return TicketResponse.from(ticketRepository.save(ticket));
    }

    @Transactional(readOnly = true)
    public List<TicketResponse> list(TicketStatus status, TicketPriority priority) {
        List<SupportTicket> tickets;
        if (status != null) {
            tickets = ticketRepository.findByStatus(status);
        } else if (priority != null) {
            tickets = ticketRepository.findByPriority(priority);
        } else {
            tickets = ticketRepository.findAll();
        }
        return tickets.stream().map(TicketResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public TicketResponse getById(Long id) {
        return TicketResponse.from(getEntity(id));
    }

    @Transactional
    public TicketResponse assign(Long id, AssignTicketRequest request) {
        SupportTicket ticket = getEntity(id);
        ticket.assignTo(request.technician());
        return TicketResponse.from(ticketRepository.save(ticket));
    }

    @Transactional
    public TicketResponse changeStatus(Long id, StatusRequest request) {
        SupportTicket ticket = getEntity(id);
        ticket.changeStatus(request.status());
        return TicketResponse.from(ticketRepository.save(ticket));
    }

    @Transactional
    public TicketResponse addComment(Long id, CommentRequest request) {
        SupportTicket ticket = getEntity(id);
        ticket.addComment(request.author(), request.message());
        return TicketResponse.from(ticketRepository.save(ticket));
    }

    private SupportTicket getEntity(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found."));
    }
}
