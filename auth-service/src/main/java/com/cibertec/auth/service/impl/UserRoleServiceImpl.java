package com.cibertec.auth.service.impl;


import com.cibertec.auth.dto.request.UserRoleRequest;
import com.cibertec.auth.dto.response.UserRoleResponse;
import com.cibertec.auth.model.UserRole;
import com.cibertec.auth.model.UserRoleId;
import com.cibertec.auth.repository.UserRoleRepository;
import com.cibertec.auth.service.UserRoleService;
import com.cibertec.auth.util.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;


    @Override
    public List<UserRoleResponse> getAllUserRoles() {
        return userRoleMapper.toDtoList(userRoleRepository.findAll());
    }

    @Override
    public UserRoleResponse assignRoleToUser(UserRoleRequest userRoleRequest) {
        UserRole userRole = userRoleMapper.toEntity(userRoleRequest);
        UserRole savedUserRole = userRoleRepository.save(userRole);
        return userRoleMapper.toDto(savedUserRole);

    }

    @Override
    public void removeRoleFromUser(Long userId, Long roleId) {
        UserRoleId id = new UserRoleId(userId, roleId);
        UserRole userRole = userRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserRole not found with userId: " + userId + " and roleId: " + roleId));
        userRoleRepository.delete(userRole);
    }

    @Override
    public List<UserRoleResponse> getUserRolesByUserId(Long userId) {
        return userRoleMapper.toDtoList(userRoleRepository.findByUserId(userId));
    }
}
