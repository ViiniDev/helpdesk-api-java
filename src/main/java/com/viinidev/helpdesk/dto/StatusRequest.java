package com.viinidev.helpdesk.dto;

import com.viinidev.helpdesk.domain.TicketStatus;
import jakarta.validation.constraints.NotNull;

public record StatusRequest(@NotNull TicketStatus status) {
}
