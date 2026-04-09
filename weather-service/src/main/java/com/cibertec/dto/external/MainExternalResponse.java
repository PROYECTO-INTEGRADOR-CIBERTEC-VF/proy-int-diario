package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record MainExternalResponse(
        Double temp,
        Double feels_like,
        Double humidity
) {
}
