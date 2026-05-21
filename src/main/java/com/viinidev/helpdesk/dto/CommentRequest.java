package com.viinidev.helpdesk.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentRequest(
        @NotBlank String author,
        @NotBlank String message
) {
}
