package com.cibertec.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RoleRequest(

        @NotBlank(message = "Role name must not be blank")
      String name,

      String description
) {
}
