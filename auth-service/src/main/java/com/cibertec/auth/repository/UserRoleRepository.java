package com.cibertec.auth.repository;

import com.cibertec.auth.model.UserRole;

import com.cibertec.auth.model.UserRoleId;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository< @NonNull UserRole, @NonNull UserRoleId> {

    Optional <UserRole> findById(Long id);

    List<UserRole> findByUserId(Long userId);
}
