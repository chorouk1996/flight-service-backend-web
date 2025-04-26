package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.dto.requests.CreatePassengerRequest;
import com.service.backend.web.models.dto.requests.UpdatePassengerRequest;
import com.service.backend.web.security.UserDetailsImpl;
import com.service.backend.web.services.interfaces.IPassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/passenger")
public class PassengerController {

    @Autowired
    IPassengerService passengerService;
    PassengerDto passenger = new PassengerDto();


    @PostMapping()
    public PassengerDto addPassenger(@RequestBody @Valid CreatePassengerRequest passenger) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passengerService.addPassenger(passenger,user.getUsername());
    }


    @GetMapping("/all")
    public List<PassengerDto> getAllPassenger() {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passengerService.getAllPassenger(user.getUsername());
    }



    @GetMapping("/{id}")
    public PassengerDto getPassenger(@PathVariable(required = true) Long id) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passengerService.getPassengerById(user.getUsername(),id);
    }


    @PutMapping("/{id}")
    public PassengerDto updatePassenger(@PathVariable(required = true) Long id, @RequestBody @Valid UpdatePassengerRequest passenger) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passengerService.updatePassengerByUser(user.getUsername(),passenger,id);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable(required = true) Long id) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         passengerService.deletePassengerByUser(user.getUsername(),id);
    }
}


