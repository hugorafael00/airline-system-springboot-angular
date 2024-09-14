package com.hr.airline.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.hr.airline.domain.Flight;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {

    @Query("{ 'flightNumber' : ?0 }")
    Optional<Flight> findByFlightNumber(String flightNumber);


    @Query("{ $and: [ { 'source' : { $regex: ?0, $options: 'i' } } , { 'destination' : { $regex: ?1, $options: 'i' } } , { 'flightNumber' : { $regex: ?2, $options: 'i' } } ] }")
    List<Flight> fullSearch(String source, String destination, String flightNumber);


}
