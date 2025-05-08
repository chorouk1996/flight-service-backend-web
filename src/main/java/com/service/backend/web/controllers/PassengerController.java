package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.SavedPassengerDto;
import com.service.backend.web.models.requests.CreateSavedPassengerRequest;
import com.service.backend.web.models.requests.UpdateSavedPassengerRequest;
import com.service.backend.web.services.helper.SecurityHelper;
import com.service.backend.web.services.interfaces.ISavedPassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return passengerService.addSavedPassenger(passenger, SecurityHelper.getUserConnected().getUsername());
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('USER')")
    public List<SavedPassengerDto> getAllSavedPassenger() {
        return passengerService.getAllSavedPassenger(SecurityHelper.getUserConnected().getUsername());
    }



    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public SavedPassengerDto getSavedPassenger(@PathVariable(required = true) Long id) {
        return passengerService.getSavedPassengerById(SecurityHelper.getUserConnected().getUsername(),id);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public SavedPassengerDto updateSavedPassenger(@PathVariable(required = true) Long id, @RequestBody @Valid UpdateSavedPassengerRequest passenger) {
        return passengerService.updateSavedPassengerByUser(SecurityHelper.getUserConnected().getUsername(),passenger,id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSavedPassenger(@PathVariable(required = true) Long id) {
         passengerService.deleteSavedPassengerByUser(SecurityHelper.getUserConnected().getUsername(),id);
    }
}


