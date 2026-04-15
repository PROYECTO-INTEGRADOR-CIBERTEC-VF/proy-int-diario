package com.cibertec.auth.util;

import com.cibertec.auth.dto.request.RoleRequest;
import com.cibertec.auth.dto.response.RoleResponse;
import com.cibertec.auth.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "userRoles", ignore = true)
    Role toEntity(RoleRequest roleRequest);

    RoleResponse toDto(Role role);

    List<RoleResponse> toDtoList(List<Role> roles);

}
