package com.kienast.userservice.controller;

import com.kienast.userservice.model.Role;
import com.kienast.userservice.model.RoleToUserDto;
import com.kienast.userservice.model.User;
import com.kienast.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = this.userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User givenUser) {
        User savedUser = this.userService.saveUser(givenUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role givenRole) {
        Role savedRole = this.userService.saveRole(givenRole);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDto roleToUserDto) {
        this.userService.addRoleToUser(roleToUserDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
