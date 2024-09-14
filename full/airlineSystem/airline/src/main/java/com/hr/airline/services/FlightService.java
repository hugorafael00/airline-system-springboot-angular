package com.hr.airline.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hr.airline.config.GenerateRandomValue;
import com.hr.airline.domain.Flight;
import com.hr.airline.domain.FlightReservation;
import com.hr.airline.domain.User;
import com.hr.airline.dto.CustomerDto;
import com.hr.airline.dto.FlightDto;
import com.hr.airline.repository.FlightRepository;
import com.hr.airline.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableCaching
@Service
public class FlightService implements Serializable {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;


    GenerateRandomValue generateRandomValue = new GenerateRandomValue();

    @Cacheable(value = "findAll")
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public Flight findById(String flightNumber) {
        return flightRepository.findById(flightNumber).orElse(null);
    }

    public String create(Flight flight) {
        flightRepository.save(flight);
        return "Flight created with sucess!";
    }

    public String delete(String flightNumber) {
        flightRepository.deleteById(flightNumber);
        return "Flight deleted with sucess!";
    }

    public String buy(String flightNumber) {
        // check if flightNumber exists
        log.info(flightNumber);
        Flight flight = flightRepository.findByFlightNumber(flightNumber).orElse(null);
        if (flight == null) {
            return "Flight not found";
        }
        // check if flight is full
        if (flight.isFull()) {
            return "Flight is full";
        }
        // String subject = jwtTokenService.getSubjectFromToken(token);
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).get();
        FlightReservation ticket = FlightReservation.builder()
        .reservationNumber(generateRandomValue.generateRandomValue(6))
        .flightDto(fromDto(flight))
        .customerDto(new CustomerDto(user))
        .build();

        user.addFlightReservation(ticket);
        flight.addPassport(user.getPassportNumber());

        flightRepository.save(flight);
        userRepository.save(user);

        return "Flight reserved with sucess!";
    }

    public List<Flight> fullSearch(String source, String destination, String flightNumber) {
        return flightRepository.fullSearch(source, destination, flightNumber);
    }

    public FlightDto fromDto(Flight flight) {
        return new FlightDto(flight.getFlightNumber(), flight.getSource(), flight.getDestination());
    }

}
