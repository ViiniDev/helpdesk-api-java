package com.viinidev.helpdesk.dto;

import com.viinidev.helpdesk.domain.SupportTicket;
import com.viinidev.helpdesk.domain.TicketPriority;
import com.viinidev.helpdesk.domain.TicketStatus;

import java.time.LocalDateTime;
import java.util.List;

public record TicketResponse(
        Long id,
        String requesterName,
        String requesterEmail,
        String title,
        String description,
        String assignedTo,
        TicketPriority priority,
        TicketStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<CommentResponse> comments
) {

    public static TicketResponse from(SupportTicket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getRequesterName(),
                ticket.getRequesterEmail(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getAssignedTo(),
                ticket.getPriority(),
                ticket.getStatus(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt(),
                ticket.getComments().stream().map(CommentResponse::from).toList()
        );
    }
}
