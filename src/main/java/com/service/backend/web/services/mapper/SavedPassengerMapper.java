package com.service.backend.web.services.mapper;

import com.service.backend.web.models.dto.SavedPassengerDto;
import com.service.backend.web.models.requests.CreateSavedPassengerRequest;
import com.service.backend.web.models.entities.SavedPassenger;

public class SavedPassengerMapper {

    private SavedPassengerMapper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    }

    public static SavedPassengerDto mapSavedPassengerEntityToDto(SavedPassenger savedPassenger) {
        SavedPassengerDto dto = new SavedPassengerDto();
        dto.setId(savedPassenger.getId());
        dto.setAge(savedPassenger.getAge());
        dto.setFirstName(savedPassenger.getFirstName());
        dto.setLastName(savedPassenger.getLastName());
        dto.setEmail(savedPassenger.getEmail());
        return dto;
    }

    public static SavedPassenger mapSavedPassengerDtoToEntity(SavedPassengerDto dto) {
        SavedPassenger savedPassenger = new SavedPassenger();
        savedPassenger.setId(dto.getId());
        savedPassenger.setAge(dto.getAge());
        savedPassenger.setFirstName(dto.getFirstName());
        savedPassenger.setLastName(dto.getLastName());
        savedPassenger.setEmail(dto.getEmail());
        return savedPassenger;
    }

    public static SavedPassenger mapCreateSavedPassengerRequestToEntity(CreateSavedPassengerRequest dto) {
        SavedPassenger savedPassenger = new SavedPassenger();
        savedPassenger.setAge(dto.getAge());
        savedPassenger.setFirstName(dto.getFirstName());
        savedPassenger.setLastName(dto.getLastName());
        savedPassenger.setEmail(dto.getEmail());
        return savedPassenger;
    }



}
