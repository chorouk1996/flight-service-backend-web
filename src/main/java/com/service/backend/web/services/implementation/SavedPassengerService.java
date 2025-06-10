package com.service.backend.web.services.implementation;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.dto.SavedPassengerDto;
import com.service.backend.web.models.requests.CreateSavedPassengerRequest;
import com.service.backend.web.models.requests.UpdateSavedPassengerRequest;
import com.service.backend.web.models.entities.SavedPassenger;
import com.service.backend.web.repositories.SavedPassengerRepository;
import com.service.backend.web.services.helper.UtilHelper;
import com.service.backend.web.services.interfaces.ISavedPassengerService;
import com.service.backend.web.services.mapper.SavedPassengerMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.service.backend.web.services.mapper.SavedPassengerMapper.mapCreateSavedPassengerRequestToEntity;
import static com.service.backend.web.services.mapper.SavedPassengerMapper.mapSavedPassengerEntityToDto;

@Service
@AllArgsConstructor
public class SavedPassengerService implements ISavedPassengerService {

    private final SavedPassengerRepository savedPassengerRepository;

    @Autowired UserService userService;
    @Override
    public SavedPassengerDto addSavedPassenger(CreateSavedPassengerRequest savedPassenger,String username) {
        SavedPassenger newSavedPassenger = mapCreateSavedPassengerRequestToEntity(savedPassenger);
        newSavedPassenger.setUser(userService.getUserByEmail(username));

        return  mapSavedPassengerEntityToDto(savedPassengerRepository.save(newSavedPassenger));
    }


    @Override
    public List<SavedPassengerDto> getAllSavedPassenger(String username) {
        return savedPassengerRepository.findByUser(userService.getUserByEmail(username)).stream().map(SavedPassengerMapper::mapSavedPassengerEntityToDto).toList();
    }

    @Override
    public SavedPassengerDto getSavedPassengerById(String username, Long id) {
        return mapSavedPassengerEntityToDto(getSavedPassengerByIdAndUser(username,id));
    }

    private SavedPassenger getSavedPassengerByIdAndUser(String username, Long id) {
        return savedPassengerRepository.findByUserAndId(userService.getUserByEmail(username),id).orElseThrow(
                () -> {
                    throw new FunctionalException("The SavedPassenger doesn't exist or don't belong to this user", HttpStatus.NOT_FOUND);
                }
        );

    }
    @Override
    public SavedPassengerDto updateSavedPassengerByUser(String username, UpdateSavedPassengerRequest savedPassenger, Long id) {
        SavedPassenger oldSavedPassenger = getSavedPassengerByIdAndUser(username,id);
        UtilHelper.updateIfNotNull(oldSavedPassenger::setAge,savedPassenger::getAge);
        UtilHelper.updateIfNotNull(oldSavedPassenger::setEmail,savedPassenger::getEmail);
        UtilHelper.updateIfNotNull(oldSavedPassenger::setFirstName,savedPassenger::getFirstName);
        UtilHelper.updateIfNotNull(oldSavedPassenger::setLastName,savedPassenger::getLastName);
        return mapSavedPassengerEntityToDto(savedPassengerRepository.save(oldSavedPassenger));
    }

    @Override
    public void deleteSavedPassengerByUser(String username, Long id) {
        savedPassengerRepository.delete(getSavedPassengerByIdAndUser(username,id));
    }




}
