package com.ust.FlightServices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private int duration; // Duration in minutes
    private double price;
    private String airlineCode;


}