package com.viinidev.helpdesk.controller;

import com.viinidev.helpdesk.domain.TicketPriority;
import com.viinidev.helpdesk.domain.TicketStatus;
import com.viinidev.helpdesk.dto.*;
import com.viinidev.helpdesk.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse create(@RequestBody @Valid TicketRequest request) {
        return ticketService.create(request);
    }

    @GetMapping
    public List<TicketResponse> list(
            @RequestParam(required = false) TicketStatus status,
            @RequestParam(required = false) TicketPriority priority
    ) {
        return ticketService.list(status, priority);
    }

    @GetMapping("/{id}")
    public TicketResponse getById(@PathVariable Long id) {
        return ticketService.getById(id);
    }

    @PatchMapping("/{id}/assign")
    public TicketResponse assign(@PathVariable Long id, @RequestBody @Valid AssignTicketRequest request) {
        return ticketService.assign(id, request);
    }

    @PatchMapping("/{id}/status")
    public TicketResponse changeStatus(@PathVariable Long id, @RequestBody @Valid StatusRequest request) {
        return ticketService.changeStatus(id, request);
    }

    @PostMapping("/{id}/comments")
    public TicketResponse addComment(@PathVariable Long id, @RequestBody @Valid CommentRequest request) {
        return ticketService.addComment(id, request);
    }
}
