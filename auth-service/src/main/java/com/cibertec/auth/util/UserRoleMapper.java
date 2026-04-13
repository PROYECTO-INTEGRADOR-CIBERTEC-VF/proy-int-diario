package com.cibertec.auth.util;

import com.cibertec.auth.dto.request.UserRoleRequest;

import com.cibertec.auth.dto.response.UserRoleResponse;
import com.cibertec.auth.model.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {

    @Mapping(source = "userId", target = "id.userId")
    @Mapping(source = "roleId", target = "id.roleId")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "roleId", target = "role.id")
    @Mapping(target = "assignedAt", ignore = true)
    UserRole toEntity(UserRoleRequest userRoleRequest);

    UserRoleResponse toDto(UserRole userRole);

    List<UserRoleResponse> toDtoList(List<UserRole> userRoles);

}
