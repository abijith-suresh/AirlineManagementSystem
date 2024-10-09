package com.ust.Airline.repo;


import com.ust.Airline.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface AirlineRepo extends JpaRepository<Airline,String> {
    Optional<Airline> findByAirlineCode(String airlineCode);
}
