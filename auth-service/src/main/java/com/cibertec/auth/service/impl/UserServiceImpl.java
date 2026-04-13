package com.cibertec.auth.service.impl;

import com.cibertec.auth.dto.request.UserRequest;
import com.cibertec.auth.dto.response.UserResponse;
import com.cibertec.auth.model.User;
import com.cibertec.auth.repository.UserRepository;
import com.cibertec.auth.service.UserService;
import com.cibertec.auth.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
       User userFound = userRepository.findById(id).orElseThrow(
               () -> new RuntimeException("User not found with id: " + id)
       );

       userFound.setEmail(userRequest.email());
       userFound.setUsername(userRequest.username());
       userFound.setFirstName(userRequest.firstName());
       userFound.setLastName(userRequest.lastName());
        if (userRequest.password() != null && !userRequest.password().isEmpty()) {
            userFound.setPassword(passwordEncoder.encode(userRequest.password()));
        }

        User updatedUser = userRepository.save(userFound);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User userFound = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id)
        );
        userRepository.delete(userFound);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return userMapper.toDto(user);
    }
}
