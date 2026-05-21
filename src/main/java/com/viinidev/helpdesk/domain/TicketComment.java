package com.viinidev.helpdesk.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ticket_comments")
public class TicketComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private SupportTicket ticket;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, length = 1500)
    private String message;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    protected TicketComment() {
    }

    public TicketComment(SupportTicket ticket, String author, String message) {
        this.ticket = ticket;
        this.author = author;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
