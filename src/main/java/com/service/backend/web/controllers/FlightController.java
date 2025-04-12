package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.FlightDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/flight")
public class FlightController {

    FlightDto flight = new FlightDto();
    @GetMapping("/:id")
    public ResponseEntity<FlightDto> getFlight(@RequestParam Long id){
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FlightDto>> getAllFlight(){
        return new ResponseEntity<>(List.of(flight), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<FlightDto> addFlight(@RequestBody FlightDto flight){
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<FlightDto> updateFlight(@RequestBody FlightDto flight){
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }
}
