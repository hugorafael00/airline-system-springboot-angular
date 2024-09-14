package com.hr.airline.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Document
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    // passportNumber terá 3 digitos

    @Id
    private String email;
    private String name;
    private String password;
    private String passportNumber;

    private List<Role> roles;


    private final List<FlightReservation> flightReservations = new ArrayList<>();

    public User(String email, String name, String password, String passportNumber, List<Role> roles) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.passportNumber = passportNumber;
        this.roles = roles;
    }

    public void setFlightReservations(List<FlightReservation> flightReservations) {
        this.flightReservations.addAll(flightReservations);
    }

    public void addFlightReservation(FlightReservation flightReservation) {
        this.flightReservations.add(flightReservation);
    }

    public void removeFlightReservation(FlightReservation flightReservation) {
        this.flightReservations.remove(flightReservation);
    }

    public List<FlightReservation> getFlightReservations() {
        return flightReservations;
    }

}
