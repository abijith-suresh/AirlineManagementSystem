package com.ust.FlightServices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private FlightDto flightdto;
    private List<PassengerDto> passengerDto;
}
