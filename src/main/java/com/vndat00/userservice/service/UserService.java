package com.vndat00.userservice.service;

import com.vndat00.userservice.domain.AppUser;
import com.vndat00.userservice.domain.Role;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser appUser);
    Role saveRole(Role role);
    List<AppUser> getUsers();
    void addRoleToUser(String username, String rolename);
    AppUser getUser(String username);
}
