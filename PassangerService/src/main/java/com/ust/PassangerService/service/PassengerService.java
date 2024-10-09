package com.ust.PassangerService.service;

import com.ust.PassangerService.model.Passenger;
import com.ust.PassangerService.repo.PassengerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepo repo;
    @Autowired
    private PnrGenerationService pnrGenerationService;

    public Passenger addPassenger(Passenger passenger) {
        passenger.setPnr(pnrGenerationService.generatePnr());
        return repo.save(passenger);
    }

    public List<Passenger> getPassengerInfo(String flightNumber) {
        return repo.findByFlightNumber(flightNumber);
    }
}
