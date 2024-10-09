package com.ust.Airline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FlightDto {
    private Long id;
    private String flightNumber;
    private String airline;
    private String departureLocation;
    private String arrivalLocation;
    private String departureTime;
    private String arrivalTime;
    private int duration;
    private double price;
    private String airlineCode;
    private List<PassengerDto> passengerDto;
}
