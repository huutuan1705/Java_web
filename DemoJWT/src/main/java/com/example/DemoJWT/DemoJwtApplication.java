package com.example.DemoJWT;

import com.example.DemoJWT.domain.Role;
import com.example.DemoJWT.domain.User;
import com.example.DemoJWT.service.UserService;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoJwtApplication {
    public static void main(String[] args) {
            SpringApplication.run(DemoJwtApplication.class, args);
    }
    
    @Bean
    CommandLineRunner run(UserService userService){
        return orgs -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
            
            userService.saveUser(new User(null, "John Travalta", "john", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Will smith", "will", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Jim Carry", "jim", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Anrold Schwarz", "anrold", "1234", new ArrayList<>()));
            
            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("john", "ROLE_MANAGER");
            userService.addRoleToUser("will", "ROLE_MANAGER");
            userService.addRoleToUser("jim", "ROLE_ADMIN");
            userService.addRoleToUser("anrold", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("anrold", "ROLE_ADMIN");
            userService.addRoleToUser("anrold", "ROLE_USER");
        };
    }
}
