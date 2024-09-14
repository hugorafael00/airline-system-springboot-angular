package com.hr.airline.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.hr.airline.domain.User;
import com.hr.airline.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class Instantiation implements CommandLineRunner {


    @Autowired
    private UserRepository userRepo;

    @Override
    public void run(String... args) throws Exception {


        User user = userRepo.findByEmail("dale@gmail.com").get();
        user = user.toBuilder().password("{bcrypt}$2a$10$NEccRPvF2JkZmFEpdf4sKedPx4sC45FmB2Pu0JWfZeUVv71X7jjry").build();

        userRepo.save(user);

    }

}
