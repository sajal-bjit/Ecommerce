// config/DataInitializer.java
package com.group3.ECommerce.config;

import com.group3.ECommerce.model.ERole;
import com.group3.ECommerce.model.Role;
import com.group3.ECommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize roles
        if (roleRepository.count() == 0) {
            Role userRole = new Role();
            userRole.setName(ERole.ROLE_USER);
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName(ERole.ROLE_ADMIN);
            roleRepository.save(adminRole);

            Role guestRole = new Role();
            guestRole.setName(ERole.ROLE_GUEST);
            roleRepository.save(guestRole);
        }
    }
}