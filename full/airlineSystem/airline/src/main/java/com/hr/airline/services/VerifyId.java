package com.hr.airline.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.hr.airline.repository.FlightRepository;
import com.hr.airline.repository.UserRepository;

public class VerifyId {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    UserRepository userRepository;

    public boolean verify(String id) {
        if (userRepository != null && flightRepository != null) {
            while (flightRepository.findByFlightNumber(id).get() != null && userRepository.findByPassportNumber(id) != null) {
                return false;
            }
        }
        return true;
    }
}
