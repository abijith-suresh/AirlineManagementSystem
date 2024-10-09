package com.ust.PassangerService.controller;

import com.ust.PassangerService.model.Passenger;
import com.ust.PassangerService.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengerinfo")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @PostMapping("/passenger")
    public Passenger addPassenger(@RequestBody Passenger passenger){
        return passengerService.addPassenger(passenger);
    }
    @GetMapping("{flightNumber}")
    public List<Passenger> getPassengerInfo(@PathVariable String flightNumber){
        return passengerService.getPassengerInfo(flightNumber);
    }

}
