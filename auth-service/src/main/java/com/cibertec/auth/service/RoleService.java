package com.cibertec.auth.service;

import com.cibertec.auth.dto.request.RoleRequest;
import com.cibertec.auth.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {

    List<RoleResponse> getAllRoles();

    RoleResponse getRoleById(Long id);

    RoleResponse createRole(RoleRequest roleRequest);

    RoleResponse updateRole(Long id, RoleRequest roleRequest);

    void deleteRole(Long id);

}
