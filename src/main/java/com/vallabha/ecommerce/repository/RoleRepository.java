package com.vallabha.ecommerce.repository;

import com.vallabha.ecommerce.model.AppRole;
import com.vallabha.ecommerce.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(AppRole roleName);
}