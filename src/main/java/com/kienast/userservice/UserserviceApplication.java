package com.kienast.userservice;

import com.kienast.userservice.model.Role;
import com.kienast.userservice.model.RoleToUserDto;
import com.kienast.userservice.model.User;
import com.kienast.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER", new ArrayList<>()));
            userService.saveRole(new Role(null, "ROLE_MANAGER", new ArrayList<>()));
            userService.saveRole(new Role(null, "ROLE_ADMIN", new ArrayList<>()));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN", new ArrayList<>()));

            userService.saveUser(new User(null, "John Doe", "johnDoe", "secret123", new ArrayList<>()));
            userService.saveUser(new User(null, "Ben Dover", "benDover", "secret123", new ArrayList<>()));
            userService.saveUser(new User(null, "Timmy Turner", "timmyTurner", "secret123", new ArrayList<>()));
            userService.saveUser(new User(null, "Lisa Simpson", "lisaSimpson", "secret123", new ArrayList<>()));

            userService.addRoleToUser(new RoleToUserDto("johnDoe", "ROLE_USER"));
            userService.addRoleToUser(new RoleToUserDto("johnDoe", "ROLE_MANGER"));
            userService.addRoleToUser(new RoleToUserDto("benDover", "ROLE_MANAGER"));
            userService.addRoleToUser(new RoleToUserDto("timmyTurner", "ROLE_ADMIN"));
            userService.addRoleToUser(new RoleToUserDto("lisaSimpson", "ROLE_SUPER_ADMIN"));
            userService.addRoleToUser(new RoleToUserDto("lisaSimpson", "ROLE_ADMIN"));
            userService.addRoleToUser(new RoleToUserDto("lisaSimpson", "ROLE_USER"));
        };
    }

}
