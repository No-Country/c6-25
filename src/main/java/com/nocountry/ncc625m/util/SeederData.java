package com.nocountry.ncc625m.util;

import com.nocountry.ncc625m.model.Role;
import com.nocountry.ncc625m.repository.RoleRepository;
import com.nocountry.ncc625m.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SeederData {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @EventListener
    public void eventListener(ContextRefreshedEvent contextRefreshedEvent) {
        if (roleRepository.findAll().isEmpty()) createRole();
    }

    public void createRole() {
        String[] roles = {"ROLE_USER", "ROLE_ADMIN"};
        for (String rol: roles) {
            roleRepository.save(
                    Role.builder()
                            .name(rol)
                            .description("")
                            .createAt(new Date())
                            .build());
        }
    }
}