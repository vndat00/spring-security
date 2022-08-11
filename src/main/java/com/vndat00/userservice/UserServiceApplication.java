package com.vndat00.userservice;

import com.vndat00.userservice.domain.AppUser;
import com.vndat00.userservice.domain.Role;
import com.vndat00.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

   /* @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/

//    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new Role(null,"ROLE_USER"));
            userService.saveRole(new Role(null,"ROLE_MANAGER"));
            userService.saveRole(new Role(null,"ROLE_ADMIN"));
            userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

            userService.saveUser(new AppUser(null, "NgocDat", "vndat00", "123abc", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "AnhDai", "anhdai", "123abc", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "ThoPham", "thopham", "123abc", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "QuocHuy", "quochuy", "123abc", new ArrayList<>()));

            userService.addRoleToUser("vndat00", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("vndat00", "ROLE_MANAGER");
            userService.addRoleToUser("anhdai", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("thopham", "ROLE_USER");
            userService.addRoleToUser("quochuy", "ROLE_USER");
        };
    }
}
