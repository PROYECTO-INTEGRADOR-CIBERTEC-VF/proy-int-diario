package com.cibertec.auth.repository;

import com.cibertec.auth.model.Role;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<@NonNull Role, Long> {
    Optional<Role> findByName(String roleUser);

}