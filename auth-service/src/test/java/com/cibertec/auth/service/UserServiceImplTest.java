package com.cibertec.auth.service;

import com.cibertec.auth.dto.request.UserRequest;
import com.cibertec.auth.dto.response.UserResponse;
import com.cibertec.auth.model.User;
import com.cibertec.auth.repository.UserRepository;
import com.cibertec.auth.service.impl.UserServiceImpl;
import com.cibertec.auth.util.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void getUserById_debeRetornarUsuario() {

        // Arrange
        Long userId = 1L;

        User user = new User();
        user.setId(userId);

        UserResponse response = UserResponse.builder()
                .id(userId)
                .email("test@email.com")
                .username("testuser")
                .firstName("Test")
                .lastName("User")
                .enabled(true)
                .createdAt(null)
                .updatedAt(null)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(response);

        // Act
        UserResponse result = userService.getUserById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.id());
        verify(userRepository).findById(userId);
    }


    @Test
    void createUser_debeGuardarUsuario() {

        // Arrange
        UserRequest request = new UserRequest(
                "testuser",
                "test@email.com",
                "123456",
                "Test",
                "User"
        );

        User user = new User();
        User savedUser = new User();

        UserResponse response = UserResponse.builder()
                .id(1L)
                .email("test@email.com")
                .username("testuser")
                .firstName("Test")
                .lastName("User")
                .enabled(true)
                .createdAt(null)
                .updatedAt(null)
                .build();

        when(userMapper.toEntity(request)).thenReturn(user);
        when(passwordEncoder.encode(request.password())).thenReturn("encodedPass");
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.toDto(savedUser)).thenReturn(response);

        // Act
        UserResponse result = userService.createUser(request);

        // Assert
        assertNotNull(result);
        verify(userRepository).save(user);
        verify(passwordEncoder).encode(request.password());
    }


    @Test
    void getUserById_debeLanzarErrorSiNoExiste() {

        Long userId = 99L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getUserById(userId);
        });

        assertTrue(exception.getMessage().contains("User not found"));
    }
}