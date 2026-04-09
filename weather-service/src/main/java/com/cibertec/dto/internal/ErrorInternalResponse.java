package com.cibertec.dto.internal;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorInternalResponse(
        LocalDateTime timestamp,
        String message
) {
}

