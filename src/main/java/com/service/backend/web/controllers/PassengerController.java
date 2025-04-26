package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.dto.SavedPassengerDto;
import com.service.backend.web.models.dto.requests.CreateSavedPassengerRequest;
import com.service.backend.web.models.dto.requests.UpdateSavedPassengerRequest;
import com.service.backend.web.security.UserDetailsImpl;
import com.service.backend.web.services.interfaces.IPassengerService;
import com.service.backend.web.services.interfaces.ISavedPassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/passenger")
public class PassengerController {

    @Autowired
    ISavedPassengerService passengerService;
    PassengerDto passenger = new PassengerDto();


    @PostMapping()
    public SavedPassengerDto addPassenger(@RequestBody @Valid CreateSavedPassengerRequest passenger) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passengerService.addSavedPassenger(passenger,user.getUsername());
    }


    @GetMapping("/all")
    public List<SavedPassengerDto> getAllPassenger() {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passengerService.getAllSavedPassenger(user.getUsername());
    }



    @GetMapping("/{id}")
    public SavedPassengerDto getPassenger(@PathVariable(required = true) Long id) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passengerService.getSavedPassengerById(user.getUsername(),id);
    }


    @PutMapping("/{id}")
    public SavedPassengerDto updatePassenger(@PathVariable(required = true) Long id, @RequestBody @Valid UpdateSavedPassengerRequest passenger) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passengerService.updateSavedPassengerByUser(user.getUsername(),passenger,id);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable(required = true) Long id) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         passengerService.deleteSavedPassengerByUser(user.getUsername(),id);
    }
}


