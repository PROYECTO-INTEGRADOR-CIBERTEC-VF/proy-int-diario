package com.cibertec.auth.service;


import com.cibertec.auth.dto.auth.AuthRequest;
import com.cibertec.auth.dto.auth.AuthResponse;
import com.cibertec.auth.dto.request.UserRequest;
import com.cibertec.auth.dto.response.UserResponse;

public interface AuthService {

    AuthResponse login (AuthRequest authRequest);

    UserResponse register(UserRequest userRequest);

    UserResponse registerAdmin(UserRequest userRequest);

}