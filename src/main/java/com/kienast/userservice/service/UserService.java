package com.kienast.userservice.service;

import com.kienast.userservice.model.Role;
import com.kienast.userservice.model.RoleToUserDto;
import com.kienast.userservice.model.User;

import java.util.List;

public interface UserService {

    public User saveUser(User user);

    public Role saveRole(Role role);

    public void addRoleToUser(RoleToUserDto roleToUserDto);

    public User getUser(String username);

    public List<User> getUsers();

}
