package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record WindMetricsApiResponse(
        Double speed
) {
}
