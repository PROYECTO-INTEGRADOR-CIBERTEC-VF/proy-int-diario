package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record MainDTO(
        Double temp,
        Double feels_like,
        Double humidity
) {
}
