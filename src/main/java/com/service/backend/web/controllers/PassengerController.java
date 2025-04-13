package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.services.inetrface.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    IPassengerService passengerService;
    PassengerDto passenger = new PassengerDto();
    @GetMapping("/:id")
    public ResponseEntity<PassengerDto> getPassenger(@RequestParam Long id){
        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PassengerDto>> getAllPassenger(){
        return new ResponseEntity<>(passengerService.getAllPassenger(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PassengerDto> addPassenger(@RequestBody PassengerDto passenger){
        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<PassengerDto> updatePassenger(@RequestBody PassengerDto passenger){
        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }
}
