package com.vndat00.userservice.service.impl;

import com.vndat00.userservice.domain.AppUser;
import com.vndat00.userservice.domain.Role;
import com.vndat00.userservice.repository.AppUserRepo;
import com.vndat00.userservice.repository.RoleRepo;
import com.vndat00.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService,UserDetailsService {
    private final AppUserRepo appUserRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepo.findByUsername(username);
        if (appUser == null){
            log.error("User not found in the database.");
            throw new UsernameNotFoundException("User not found in the database.");
        } else {
            log.info("Found user with username: {} in the database.", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new User(username, appUser.getPassword(), authorities);
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        log.info("Saving new user {} to the database.", appUser.getName());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepo.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database.", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all users.");
        return appUserRepo.findAll();
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        log.info("Adding role {} to user {} to the database.", rolename, username);
        AppUser appUser = appUserRepo.findByUsername(username);
        Role role = roleRepo.findByName(rolename);
        appUser.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user {}", username)  ;
        return appUserRepo.findByUsername(username);
    }
}
