package com.vndat00.userservice.repository;

import com.vndat00.userservice.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String name);
}
