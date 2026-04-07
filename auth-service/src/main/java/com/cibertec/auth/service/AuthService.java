package com.cibertec.auth.service;

import com.cibertec.auth.dto.request.RegisterRequest;
import com.cibertec.auth.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
}