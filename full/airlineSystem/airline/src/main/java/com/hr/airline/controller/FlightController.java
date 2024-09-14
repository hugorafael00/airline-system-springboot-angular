package com.hr.airline.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hr.airline.controller.util.URL;
import com.hr.airline.domain.Flight;
import com.hr.airline.services.FlightService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/flight")
public class FlightController implements Serializable {

    @Autowired
    private FlightService flightService;


    @GetMapping
    public ResponseEntity<List<Flight>> FindAll() {
        log.info("Requested list of flights");
        List<Flight> list = flightService.findAll();
        return ResponseEntity.ok().body(list);
    }

    // find flight by id
    @GetMapping("/{flightNumber}")
    public ResponseEntity<Flight> findById(@PathVariable String flightNumber) {
        Flight flight = flightService.findById(flightNumber);
        if (flight == null) {
            return ResponseEntity.notFound().build();
        }
        log.info("Requested flight");
        return ResponseEntity.ok().body(flight);
    }

    // find flight by source and/or destination
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> findBySourceAndDestination(@RequestParam(value = "source", defaultValue = "") String source,
                                        @RequestParam(value = "destination", defaultValue = "") String destination,
                                        @RequestParam(value = "flightNumber", defaultValue = "") String flightNumber) {
        source = URL.decodeParam(source);
        destination = URL.decodeParam(destination);
        flightNumber = URL.decodeParam(flightNumber);
        List<Flight> list = flightService.fullSearch(source, destination, flightNumber);
        log.info("Requested list of flights");
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Flight flight) {
        String response = flightService.create(flight);
        log.info(response);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody String flightNumber) {
        String response = flightService.delete(flightNumber);
        log.info(response);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buy(@RequestBody Map<String, String> obj) {
        log.info(obj.get("flightNumber"));
        String response = flightService.buy(obj.get("flightNumber"));
        log.info(response);
        return ResponseEntity.ok().body(response);
    }

}
