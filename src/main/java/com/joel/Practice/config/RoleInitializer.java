package com.joel.Practice.config;

import com.joel.Practice.model.entity.Role;
import com.joel.Practice.repo.RoleRepo;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoleInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public void run(String @NonNull ... args) throws Exception {
     List<String> roles = Arrays.asList("ROLE_PATIENT", "ROLE_ADMIN", "ROLE_DOCTOR");

     for(String role:roles){
         if(roleRepo.findByRole(role).isEmpty()){
             Role role1 = Role.builder().role(role).build();
             roleRepo.save(role1);
             System.out.printf("Role has been added to roles: %s\n",role);
         }
     }
    }
}
