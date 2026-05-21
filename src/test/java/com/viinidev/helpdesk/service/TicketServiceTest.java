package com.viinidev.helpdesk.service;

import com.viinidev.helpdesk.domain.TicketPriority;
import com.viinidev.helpdesk.domain.TicketStatus;
import com.viinidev.helpdesk.dto.CommentRequest;
import com.viinidev.helpdesk.dto.StatusRequest;
import com.viinidev.helpdesk.dto.TicketRequest;
import com.viinidev.helpdesk.dto.TicketResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @Test
    void shouldNotAllowCommentsOnClosedTicket() {
        TicketResponse ticket = ticketService.create(new TicketRequest(
                "Ana Silva",
                "ana@email.com",
                "Erro ao acessar sistema",
                "Usuario relata falha ao tentar acessar o painel principal.",
                TicketPriority.HIGH
        ));

        ticketService.changeStatus(ticket.id(), new StatusRequest(TicketStatus.CLOSED));

        assertThatThrownBy(() -> ticketService.addComment(
                ticket.id(),
                new CommentRequest("Vinicius", "Tentando comentar em chamado fechado.")
        )).isInstanceOf(IllegalStateException.class)
                .hasMessage("Closed tickets cannot be changed.");
    }
}
