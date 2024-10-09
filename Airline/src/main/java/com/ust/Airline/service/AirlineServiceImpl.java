package com.ust.Airline.service;

import com.ust.Airline.dto.AirlineDto;
import com.ust.Airline.dto.FlightDto;
import com.ust.Airline.dto.PassengerDto;
import com.ust.Airline.dto.ResponseDto;
import com.ust.Airline.model.Airline;
import com.ust.Airline.repo.AirlineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {
    @Autowired
    private AirlineRepo repo;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Airline addAirline(Airline airline) {
        return repo.save(airline);
    }

    @Override
    public ResponseDto getAirline(String airlineCode) {
        ResponseDto responseDto = new ResponseDto();
        Airline airline = repo.findByAirlineCode(airlineCode)
                .orElseThrow(() -> new RuntimeException("Airline not found"));
        AirlineDto airlineDto = mapToAirline(airline);

        // Fetch flights for the airline
        List<FlightDto> flightDTOList = webClientBuilder.baseUrl("http://localhost:9098")
                .build()
                .get()
                .uri("/flights/" + airline.getAirlineCode())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<FlightDto>>() {})
                .block();

        // Fetch passengers for each flight
        for (FlightDto flightDTO : flightDTOList) {
            List<PassengerDto> passengerDTOList = webClientBuilder.baseUrl("http://localhost:9099")
                    .build()
                    .get()
                    .uri("/passengerinfo/" + flightDTO.getFlightNumber())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<PassengerDto>>() {})
                    .block();

            flightDTO.setPassengerDto(passengerDTOList);
        }

        responseDto.setAirline(airlineDto);
        responseDto.setFlight(flightDTOList);

        return responseDto;
    }

    private AirlineDto mapToAirline(Airline airline) {
        AirlineDto dto = new AirlineDto();
        dto.setAirlineName(airline.getAirlineName());
        dto.setAirlineCode(airline.getAirlineCode());
        dto.setCountry(airline.getCountry());
        dto.setHeadquarters(airline.getHeadquarters());
        dto.setCeo(airline.getCeo());
        dto.setFoundedYear(airline.getFoundedYear());
        dto.setHubAirport(airline.getHubAirport());
        dto.setWebsite(airline.getWebsite());
        dto.setFleetSize(airline.getFleetSize());
        return dto;
    }
}