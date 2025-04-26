package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.SavedPassengerDto;
import com.service.backend.web.models.dto.requests.CreateSavedPassengerRequest;
import com.service.backend.web.models.dto.requests.UpdateSavedPassengerRequest;

import java.util.List;

public interface ISavedPassengerService {

    SavedPassengerDto addSavedPassenger(CreateSavedPassengerRequest passenger, String username);

     List<SavedPassengerDto> getAllSavedPassenger(String username);

    SavedPassengerDto getSavedPassengerById(String username,Long id);

    SavedPassengerDto updateSavedPassengerByUser(String username, UpdateSavedPassengerRequest passenger , Long id);

    void deleteSavedPassengerByUser(String username,Long id);

}
