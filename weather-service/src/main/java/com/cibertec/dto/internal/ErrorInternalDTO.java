package com.cibertec.dto.internal;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorInternalDTO(
        LocalDateTime timestamp,
        String message
) {
}

