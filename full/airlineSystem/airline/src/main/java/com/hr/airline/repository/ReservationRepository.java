package com.hr.airline.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.hr.airline.domain.FlightReservation;
import com.hr.airline.dto.CustomerDto;

@Repository
public interface ReservationRepository extends MongoRepository<FlightReservation, String> {

    @Query("{'customerDto': ?0}")
    List<FlightReservation> findByCustomerDto(CustomerDto customerDto);
}
