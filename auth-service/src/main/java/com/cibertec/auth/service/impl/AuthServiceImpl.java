package com.cibertec.auth.service.impl;

import com.cibertec.auth.dto.auth.AuthRequest;
import com.cibertec.auth.dto.auth.AuthResponse;
import com.cibertec.auth.dto.request.UserRequest;
import com.cibertec.auth.dto.response.UserResponse;
import com.cibertec.auth.model.Role;
import com.cibertec.auth.model.User;

import com.cibertec.auth.exception.ResourceNotFoundException;
import com.cibertec.auth.model.UserRole;
import com.cibertec.auth.model.UserRoleId;
import com.cibertec.auth.repository.RoleRepository;
import com.cibertec.auth.repository.UserRepository;
import com.cibertec.auth.repository.UserRoleRepository;
import com.cibertec.auth.security.JwtUtil;
import com.cibertec.auth.service.AuthService;
import com.cibertec.auth.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.emailOrUsername(),
                        authRequest.password()
                )
        );
        User user = userRepository.findByEmailOrUsername(authRequest.emailOrUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        List<String> roles = userRoles.stream()
                .map(ur -> ur.getRole().getName())
                .collect(Collectors.toList());
        String token = jwtUtil.generateToken(user.getUsername(), roles);
        return AuthResponse.builder()
                .id(user.getId())
                .token(token)
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(roles)
                .build();
    }

    @Override
    public UserResponse register(UserRequest userRequest) {
        System.out.println("Registering user: " + userRequest);
        User user = userMapper.toEntity(userRequest);
        System.out.println("Mapped user entity: " + user);
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setEnabled(true);
        User savedUser = userRepository.save(user);

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ResourceNotFoundException("No existe el rol ROLE_USER"));
        UserRoleId userRoleId = new UserRoleId(
                savedUser.getId(),
                userRole.getId()
        );
        UserRole userRoleEntity = UserRole.builder()
                .id(userRoleId)
                .user(savedUser)
                .role(userRole)
                .build();
        userRoleRepository.save(userRoleEntity);
        return userMapper.toDto(savedUser);
    }


    @Override
    public UserResponse registerAdmin(UserRequest userRequest) {
        System.out.println("Registering user: " + userRequest);
        User user = userMapper.toEntity(userRequest);
        System.out.println("Mapped user entity: " + user);
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setEnabled(true);
        User savedUser = userRepository.save(user);

        Role userRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Role ADMIN not found"));

        UserRoleId userRoleId = new UserRoleId(
                savedUser.getId(),
                userRole.getId()
        );
        UserRole userRoleEntity = UserRole.builder()
                .id(userRoleId)
                .user(savedUser)
                .role(userRole)
                .build();
        userRoleRepository.save(userRoleEntity);
        return userMapper.toDto(savedUser);
    }
}