package com.ust.FlightServices.controller;

import com.ust.FlightServices.dto.ResponseDto;
import com.ust.FlightServices.model.Flight;
import com.ust.FlightServices.service.FlightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/addflight")
    public Flight addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }
    @GetMapping("{airlinecode}")
    public List<Flight> getFlightsByAirline(@PathVariable String airlinecode){
        return flightService.getFlightsByAirline(airlinecode);
    }
    @GetMapping("withpassanger/{flightNumber}")
    public ResponseEntity<ResponseDto> getUser(@PathVariable("flightNumber") String flightNumber){
        ResponseDto responseDto = flightService.getAirline(flightNumber);
        return ResponseEntity.ok(responseDto);
    }


}
