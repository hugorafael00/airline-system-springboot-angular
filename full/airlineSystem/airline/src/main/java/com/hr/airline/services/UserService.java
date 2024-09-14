package com.hr.airline.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hr.airline.config.GenerateRandomValue;
import com.hr.airline.config.JwtTokenService;
import com.hr.airline.config.SecurityConfiguration;
import com.hr.airline.config.details.UserDetailsImpl;
import com.hr.airline.domain.FlightReservation;
import com.hr.airline.domain.Role;
import com.hr.airline.domain.User;
import com.hr.airline.dto.CreateUserDto;
import com.hr.airline.dto.CustomerDto;
import com.hr.airline.dto.LoginUserDto;
import com.hr.airline.dto.RecoveryJwtTokenDto;
import com.hr.airline.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfig;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    GenerateRandomValue generateRandomValue = new GenerateRandomValue();

    public List<FlightReservation> myReservations() {
        log.info("Requested list of reservations.");
        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        log.info(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).get().toString());
        User user = userRepository.findByEmail(email).get();
        List<FlightReservation> list = user.getFlightReservations();
        return list;
    }

    public CustomerDto fromDto(User user) {
        return new CustomerDto(user);
    }

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        log.info(loginUserDto.toString());
        log.info("Login Attempt");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());
        log.info(usernamePasswordAuthenticationToken.toString());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        log.info("Login Success");

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        log.info(userDetails.toString());

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    public Boolean createUser(CreateUserDto createUserDto) {
        if (userRepository.findByEmail(createUserDto.email()) != null) {
            return false;
        }


        User user = User.builder()
                .email(createUserDto.email())
                .name(createUserDto.name())
                .password(securityConfig.passwordEncoder().encode(createUserDto.password()))
                .passportNumber(generateRandomValue.generateRandomValue(3))
                .roles(List.of(Role.builder().name(createUserDto.role()).build()))
                .build();

        userRepository.save(user);

        return true;
    }
}
