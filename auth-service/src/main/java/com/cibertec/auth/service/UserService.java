package com.cibertec.auth.service;



import com.cibertec.auth.dto.request.UserRequest;
import com.cibertec.auth.dto.response.UserResponse;

import java.util.List;


public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);

    UserResponse getUserByEmail(String email);

    UserResponse getUserByUsername(String username);



}
