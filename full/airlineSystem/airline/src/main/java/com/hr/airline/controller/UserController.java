package com.hr.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.airline.domain.FlightReservation;
import com.hr.airline.dto.CreateUserDto;
import com.hr.airline.dto.LoginUserDto;
import com.hr.airline.dto.RecoveryJwtTokenDto;
import com.hr.airline.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/user/myflights")
    public ResponseEntity<List<FlightReservation>> myReservations() {
        List<FlightReservation> list = userService.myReservations();
        log.info("userController myReservations");
        return ResponseEntity.ok().body(list);
    }

    // logout
    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> login(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
        log.info("User logged in");
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CreateUserDto createUserDto) {
        if(userService.createUser(createUserDto) == false) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        log.info("User registered");
        return ResponseEntity.ok().body("User registered");
    }


}
