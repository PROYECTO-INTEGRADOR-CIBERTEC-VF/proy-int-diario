package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record WindExternalResponse(
        Double speed
) {
}
