package com.viinidev.helpdesk.dto;

import com.viinidev.helpdesk.domain.TicketPriority;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketRequest(
        @NotBlank String requesterName,
        @NotBlank @Email String requesterEmail,
        @NotBlank String title,
        @NotBlank String description,
        @NotNull TicketPriority priority
) {
}
