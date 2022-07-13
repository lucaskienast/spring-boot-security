package com.kienast.userservice.service;

import com.kienast.userservice.model.Role;
import com.kienast.userservice.model.User;
import com.kienast.userservice.repository.RoleRepository;
import com.kienast.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        LOG.info("UserServiceImpl saveUser -> {}", user);
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        LOG.info("UserServiceImpl saveRole -> {}", role);
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        LOG.info("UserServiceImpl addRoleToUser username -> " + username + ", and roleName -> " + roleName);
        User user = userRepository.findByUsername(username);
        LOG.info("UserServiceImpl addRoleToUser found user -> {}", user);
        Role role = roleRepository.findByName(roleName);
        LOG.info("UserServiceImpl addRoleToUser found role -> {}", role);
        user.getRoles().add(role);
        LOG.info("UserServiceImpl addRoleToUser edited user -> {}", user);
    }

    @Override
    public User getUser(String username) {
        LOG.info("UserServiceImpl getUser username -> {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        LOG.info("UserServiceImpl getUsers");
        return userRepository.findAll();
    }

}
