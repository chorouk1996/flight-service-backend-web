package com.service.backend.web.services.mapper;

import com.service.backend.web.models.dto.SavedPassengerDto;
import com.service.backend.web.models.dto.requests.CreateSavedPassengerRequest;
import com.service.backend.web.models.entities.SavedPassenger;

public class SavedPassengerMapper {

    private SavedPassengerMapper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    }

    public static SavedPassengerDto mapSavedPassengerEntityToDto(SavedPassenger SavedPassenger) {
        SavedPassengerDto dto = new SavedPassengerDto();
        dto.setId(SavedPassenger.getId());
        dto.setAge(SavedPassenger.getAge());
        dto.setFirstName(SavedPassenger.getFirstName());
        dto.setLastName(SavedPassenger.getLastName());
        dto.setEmail(SavedPassenger.getEmail());
        return dto;
    }

    public static SavedPassenger mapSavedPassengerDtoToEntity(SavedPassengerDto dto) {
        SavedPassenger SavedPassenger = new SavedPassenger();
        SavedPassenger.setId(dto.getId());
        SavedPassenger.setAge(dto.getAge());
        SavedPassenger.setFirstName(dto.getFirstName());
        SavedPassenger.setLastName(dto.getLastName());
        SavedPassenger.setEmail(dto.getEmail());
        return SavedPassenger;
    }

    public static SavedPassenger mapCreateSavedPassengerRequestToEntity(CreateSavedPassengerRequest dto) {
        SavedPassenger SavedPassenger = new SavedPassenger();
        SavedPassenger.setAge(dto.getAge());
        SavedPassenger.setFirstName(dto.getFirstName());
        SavedPassenger.setLastName(dto.getLastName());
        SavedPassenger.setEmail(dto.getEmail());
        return SavedPassenger;
    }



}
