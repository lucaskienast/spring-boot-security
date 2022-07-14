package com.kienast.userservice.service;

import com.kienast.userservice.model.Role;
import com.kienast.userservice.model.RoleToUserDto;
import com.kienast.userservice.model.User;
import com.kienast.userservice.repository.RoleRepository;
import com.kienast.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOG.info("UserServiceImpl loadUserByUsername username -> {}", username);
        User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found in database");
        } else {
            LOG.info("UserServiceImpl loadUserByUsername found user -> {}", user);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

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
    public void addRoleToUser(RoleToUserDto roleToUserDto) {
        LOG.info("UserServiceImpl addRoleToUser username -> {}, and roleName -> {}", roleToUserDto.getUsername(), roleToUserDto.getRoleName());
        User user = userRepository.findByUsername(roleToUserDto.getUsername());
        LOG.info("UserServiceImpl addRoleToUser found user -> {}", user);
        Role role = roleRepository.findByName(roleToUserDto.getRoleName());
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
