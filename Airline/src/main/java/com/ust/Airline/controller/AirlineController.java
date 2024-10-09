package com.ust.Airline.controller;





import com.ust.Airline.dto.ResponseDto;
import com.ust.Airline.model.Airline;
import com.ust.Airline.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/airline")
public class AirlineController {

    @Autowired
    private AirlineService service;

    @PostMapping("/addairline")
    public ResponseEntity<Airline> addairline(@RequestBody Airline airline){
        return ResponseEntity.ok(service.addAirline(airline));

    }
    @GetMapping("{airlineCode}")
    public ResponseEntity<ResponseDto> getUser(@PathVariable("airlineCode") String airlineCode){
        ResponseDto responseDto = service.getAirline(airlineCode);
        return ResponseEntity.ok(responseDto);
    }


}
