package com.ust.Airline.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirlineDto {
    private String airlineName;
    private String airlineCode;
    private String country;
    private String headquarters;
    private String ceo;
    private int foundedYear;
    private String hubAirport;
    private String website;
    private int fleetSize;
}
