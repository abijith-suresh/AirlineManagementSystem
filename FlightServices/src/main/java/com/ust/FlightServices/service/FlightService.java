package com.ust.FlightServices.service;

import com.ust.FlightServices.dto.FlightDto;
import com.ust.FlightServices.dto.PassengerDto;
import com.ust.FlightServices.dto.ResponseDto;
import com.ust.FlightServices.model.Flight;
import com.ust.FlightServices.repo.FlightRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepo repo;

    @Autowired
    private WebClient webClient;

    public Flight addFlight(Flight flight) {
        return repo.save(flight);
    }

    public List<Flight> getFlightsByAirline(String airlineCode) {
        return repo.findByAirlineCode(airlineCode);
    }

    @CircuitBreaker(name = "flightService", fallbackMethod = "fallbackGetAirline")
    public ResponseDto getAirline(String flightNumber) {
        ResponseDto responseDto = new ResponseDto();
        Flight flight = repo.findByFlightNumber(flightNumber).orElseThrow(() -> new RuntimeException("Flight not found"));
        FlightDto flightDto = mapToFlightdto(flight);

        List<PassengerDto> passengerdtolist = webClient.get()
                .uri("http://localhost:9099/passangerinfo/" + flight.getFlightNumber())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PassengerDto>>() {})
                .block();

        responseDto.setFlightdto(flightDto);
        responseDto.setPassengerDto(passengerdtolist);

        return responseDto;
    }

    public ResponseDto fallbackGetAirline(String flightNumber, Throwable throwable) {
        // Handle the fallback logic, e.g., return a default response or log the error
        return new ResponseDto(); // Return an empty response or a default response
    }

    private FlightDto mapToFlightdto(Flight flight) {
        FlightDto flightdto = new FlightDto();
        flightdto.setId(flight.getId());
        flightdto.setFlightNumber(flight.getFlightNumber());
        flightdto.setAirlineCode(flight.getAirlineCode());
        flightdto.setDepartureLocation(flight.getDepartureLocation());
        flightdto.setArrivalLocation(flight.getArrivalLocation());
        flightdto.setDepartureTime(flight.getDepartureTime());
        flightdto.setArrivalTime(flight.getArrivalTime());
        flightdto.setDuration(flight.getDuration());
        flightdto.setPrice(flight.getPrice());
        flightdto.setAirline(flight.getAirline());
        return flightdto;
    }
}