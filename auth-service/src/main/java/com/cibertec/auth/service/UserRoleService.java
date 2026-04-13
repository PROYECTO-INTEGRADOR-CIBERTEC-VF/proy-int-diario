package com.cibertec.auth.service;


import com.cibertec.auth.dto.request.UserRoleRequest;
import com.cibertec.auth.dto.response.UserRoleResponse;

import java.util.List;

public interface UserRoleService {

    List<UserRoleResponse> getAllUserRoles();

    UserRoleResponse assignRoleToUser(UserRoleRequest userRoleRequest);

    void  removeRoleFromUser(Long userId, Long roleId);

    List<UserRoleResponse> getUserRolesByUserId(Long userId);

}
