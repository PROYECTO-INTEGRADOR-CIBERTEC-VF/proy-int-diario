package com.cibertec.auth.service.impl;

import com.cibertec.auth.dto.request.RegisterRequest;
import com.cibertec.auth.dto.response.AuthResponse;
import com.cibertec.auth.entity.Role;
import com.cibertec.auth.entity.User;
import com.cibertec.auth.exception.BadRequestException;
import com.cibertec.auth.exception.ResourceNotFoundException;
import com.cibertec.auth.repository.RoleRepository;
import com.cibertec.auth.repository.UserRepository;
import com.cibertec.auth.service.AuthService;
import com.cibertec.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new BadRequestException("El email ya está registrado");
        }

        if (userRepository.existsByUsername(request.username())) {
            throw new BadRequestException("El username ya está registrado");
        }

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ResourceNotFoundException("No existe el rol ROLE_USER"));

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .passwordHash(passwordEncoder.encode(request.password()))
                .firstName(request.firstName())
                .lastName(request.lastName())
                .enabled(true)
                .roles(Set.of(userRole))
                .build();

        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken(savedUser);

        return new AuthResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                token,
                savedUser.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );
    }
}