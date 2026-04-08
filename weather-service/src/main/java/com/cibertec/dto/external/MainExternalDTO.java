package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record MainExternalDTO(
        Double temp,
        Double feels_like,
        Double humidity
) {
}
