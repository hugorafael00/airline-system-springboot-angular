package com.hr.airline.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;

    // flight number terá 4 digitos

    @Id
    private String flightNumber;
    private String source;
    private String destination;
    private int capacity;
    private int numberOfCustomers;

    List<String> listOfCustomers = new ArrayList<>();

    public Flight(String flightNumber, String source, String destination, int capacity) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.capacity = capacity;
    }

    public void addPassport(String passportNumber) {
        this.listOfCustomers.add(passportNumber);
        this.numberOfCustomers++;
    }

    public void removePassport(String passportNumber) {
        this.listOfCustomers.remove(passportNumber);
        this.numberOfCustomers--;
    }

    public boolean isFull() {
        return numberOfCustomers == capacity;
    }
}
