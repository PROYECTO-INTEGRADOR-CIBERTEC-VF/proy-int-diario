package com.cibertec.dto.internal;

import lombok.Builder;

@Builder
public record CityResponse(
        Long id,
        String name,
        String country,
        String state
) {
}
