package com.viinidev.helpdesk.dto;

import com.viinidev.helpdesk.domain.TicketComment;

import java.time.LocalDateTime;

public record CommentResponse(Long id, String author, String message, LocalDateTime createdAt) {

    public static CommentResponse from(TicketComment comment) {
        return new CommentResponse(comment.getId(), comment.getAuthor(), comment.getMessage(), comment.getCreatedAt());
    }
}
