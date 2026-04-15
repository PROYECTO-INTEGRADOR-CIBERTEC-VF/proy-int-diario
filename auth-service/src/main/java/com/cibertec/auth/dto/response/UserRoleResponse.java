package com.cibertec.auth.dto.response;

import lombok.Builder;

@Builder
public record UserRoleResponse(

        UserResponse user,
        RoleResponse role
) {
}
