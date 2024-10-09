package com.ust.FlightServices.repo;


import com.ust.FlightServices.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepo extends JpaRepository<Flight,Long> {
    List<Flight> findByAirlineCode(String airlineCode);

    Optional<Flight> findByFlightNumber(String flightNumber);
}
