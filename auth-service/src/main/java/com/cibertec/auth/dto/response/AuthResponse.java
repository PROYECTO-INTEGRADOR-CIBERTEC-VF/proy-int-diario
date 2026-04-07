package com.cibertec.auth.dto.response;

import java.util.Set;

public record AuthResponse(
        Long id,
        String username,
        String email,
        String token,
        Set<String> roles
) {
}