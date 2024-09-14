package com.hr.airline.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.hr.airline.dto.CustomerDto;
import com.hr.airline.dto.FlightDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class FlightReservation implements Serializable {

    private static final long serialVersionUID = 1L;

    // ReservationNumber terá 6 digitos

    @Id
    private String reservationNumber;
    private FlightDto flightDto;
    private CustomerDto customerDto;


    public FlightReservation(String reservationNumber, FlightDto flightDto, CustomerDto customerDto) {
        this.reservationNumber = reservationNumber;
        this.flightDto = flightDto;
        this.customerDto = customerDto;
    }

}
