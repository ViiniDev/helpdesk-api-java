package com.viinidev.helpdesk.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "support_tickets")
public class SupportTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String requesterName;

    @Column(nullable = false)
    private String requesterEmail;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    private String assignedTo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status = TicketStatus.OPEN;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketComment> comments = new ArrayList<>();

    protected SupportTicket() {
    }

    public SupportTicket(String requesterName, String requesterEmail, String title, String description, TicketPriority priority) {
        this.requesterName = requesterName;
        this.requesterEmail = requesterEmail;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public String getRequesterEmail() {
        return requesterEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<TicketComment> getComments() {
        return comments;
    }

    public void assignTo(String technician) {
        this.assignedTo = technician;
        this.status = TicketStatus.IN_PROGRESS;
        touch();
    }

    public void changeStatus(TicketStatus status) {
        if (this.status == TicketStatus.CLOSED) {
            throw new IllegalStateException("Closed tickets cannot be changed.");
        }
        this.status = status;
        touch();
    }

    public void addComment(String author, String message) {
        this.comments.add(new TicketComment(this, author, message));
        touch();
    }

    private void touch() {
        this.updatedAt = LocalDateTime.now();
    }
}
