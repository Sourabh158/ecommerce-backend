package com.vallabha.ecommerce;

import com.vallabha.ecommerce.model.AppRole;
import com.vallabha.ecommerce.model.Role;
import com.vallabha.ecommerce.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if ROLE_USER exists, if not, create it
        if (roleRepository.findByRoleName(AppRole.ROLE_USER).isEmpty()) {
            Role userRole = new Role(AppRole.ROLE_USER);
            roleRepository.save(userRole);
            System.out.println("Role ROLE_USER created.");
        }

        // Check if ROLE_ADMIN exists, if not, create it
        if (roleRepository.findByRoleName(AppRole.ROLE_ADMIN).isEmpty()) {
            Role adminRole = new Role(AppRole.ROLE_ADMIN);
            roleRepository.save(adminRole);
            System.out.println("Role ROLE_ADMIN created.");
        }
    }
}