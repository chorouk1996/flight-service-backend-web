package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.SavedPassengerDto;
import com.service.backend.web.models.requests.CreateSavedPassengerRequest;
import com.service.backend.web.models.requests.UpdateSavedPassengerRequest;
import com.service.backend.web.security.UserDetailsImpl;
import com.service.backend.web.services.interfaces.ISavedPassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/passengers")
public class PassengerController {

    @Autowired
    ISavedPassengerService passengerService;


    @PostMapping()
    @PreAuthorize("hasAuthority('USER')")
    public SavedPassengerDto addSavedPassenger(@RequestBody @Valid CreateSavedPassengerRequest passenger) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passengerService.addSavedPassenger(passenger,user.getUsername());
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('USER')")
    public List<SavedPassengerDto> getAllSavedPassenger() {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passengerService.getAllSavedPassenger(user.getUsername());
    }



    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public SavedPassengerDto getSavedPassenger(@PathVariable(required = true) Long id) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passengerService.getSavedPassengerById(user.getUsername(),id);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public SavedPassengerDto updateSavedPassenger(@PathVariable(required = true) Long id, @RequestBody @Valid UpdateSavedPassengerRequest passenger) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passengerService.updateSavedPassengerByUser(user.getUsername(),passenger,id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSavedPassenger(@PathVariable(required = true) Long id) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         passengerService.deleteSavedPassengerByUser(user.getUsername(),id);
    }
}


