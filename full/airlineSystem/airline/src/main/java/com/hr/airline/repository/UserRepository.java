package com.hr.airline.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.hr.airline.domain.FlightReservation;
import com.hr.airline.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    User findByPassportNumber(String passportNumber);

    @Query("{'email': ?0}")
    List<FlightReservation> findReservationsByEmail(String email);
}
