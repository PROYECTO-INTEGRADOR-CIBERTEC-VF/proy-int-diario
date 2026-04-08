package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record WindExternalDTO(
        Double speed
) {
}
