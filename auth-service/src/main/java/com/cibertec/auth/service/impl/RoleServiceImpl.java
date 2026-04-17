package com.cibertec.auth.service.impl;


import com.cibertec.auth.dto.request.RoleRequest;
import com.cibertec.auth.dto.response.RoleResponse;
import com.cibertec.auth.model.Role;
import com.cibertec.auth.repository.RoleRepository;
import com.cibertec.auth.service.RoleService;
import com.cibertec.auth.util.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private  final RoleRepository roleRepository;

    private final RoleMapper roleMapper;


    @Override
    public List<RoleResponse> getAllRoles() {
        return roleMapper.toDtoList(roleRepository.findAll());
    }

    @Override
    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        return roleMapper.toDto(role);
    }

    @Override
    public RoleResponse createRole(RoleRequest roleRequest) {
        Role role = roleMapper.toEntity(roleRequest);
        Role savedRole = roleRepository.save(role);
        return roleMapper.toDto(savedRole);
    }

    @Override
    public RoleResponse updateRole(Long id, RoleRequest roleRequest) {
        Role roleFound = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        roleFound.setName(roleRequest.name());
        roleFound.setDescription(roleRequest.description());
        Role updatedRole = roleRepository.save(roleFound);
        return roleMapper.toDto(updatedRole);

    }

    @Override
    public void deleteRole(Long id) {
        Role roleFound = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        roleRepository.delete(roleFound);
    }
}
