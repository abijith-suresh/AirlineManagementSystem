package com.ust.PassangerService.repo;

import com.ust.PassangerService.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepo extends JpaRepository<Passenger,String> {
    boolean existsByPnr(String pnr);
    List<Passenger> findByFlightNumber(String flightNumber);
}
