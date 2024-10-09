package com.ust.PassangerService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="passengerinfo")
public class Passenger {
    @Id
    private String pnr;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String flightNumber;

}
