package com.cibertec.auth.service;

import com.cibertec.auth.entity.User;

public interface JwtService {
    String generateToken(User user);
}