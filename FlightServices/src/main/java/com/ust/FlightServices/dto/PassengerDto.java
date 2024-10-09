package com.ust.FlightServices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {
    private String pnr;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String flightNumber;
}
