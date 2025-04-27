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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.service.backend.web.services.mapper.SavedPassengerMapper.mapCreateSavedPassengerRequestToEntity;
import static com.service.backend.web.services.mapper.SavedPassengerMapper.mapSavedPassengerEntityToDto;

@Service
public class SavedPassengerService implements ISavedPassengerService {

    SavedPassengerRepository SavedPassengerRepository;

    @Autowired UserService userService;
    @Override
    public SavedPassengerDto addSavedPassenger(CreateSavedPassengerRequest SavedPassenger,String username) {
        SavedPassenger newSavedPassenger = mapCreateSavedPassengerRequestToEntity(SavedPassenger);
        newSavedPassenger.setUser(userService.getUserById(username));

        return  mapSavedPassengerEntityToDto(SavedPassengerRepository.save(newSavedPassenger));
    }


    @Override
    public List<SavedPassengerDto> getAllSavedPassenger(String username) {
        return SavedPassengerRepository.findByUser(userService.getUserById(username)).stream().map(SavedPassengerMapper::mapSavedPassengerEntityToDto).toList();
    }

    @Override
    public SavedPassengerDto getSavedPassengerById(String username, Long id) {
        return mapSavedPassengerEntityToDto(getSavedPassengerByIdAndUser(username,id));
    }

    private SavedPassenger getSavedPassengerByIdAndUser(String username, Long id) {
        return SavedPassengerRepository.findByUserAndId(userService.getUserById(username),id).orElseThrow(
                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto("The SavedPassenger doesn't exist or don't belong to this user", HttpStatus.NOT_FOUND));
                }
        );

    }
    @Override
    public SavedPassengerDto updateSavedPassengerByUser(String username, UpdateSavedPassengerRequest SavedPassenger, Long id) {
        SavedPassenger oldSavedPassenger = getSavedPassengerByIdAndUser(username,id);
        UtilHelper.updateIfNotNull(oldSavedPassenger::setAge,SavedPassenger::getAge);
        UtilHelper.updateIfNotNull(oldSavedPassenger::setEmail,SavedPassenger::getEmail);
        UtilHelper.updateIfNotNull(oldSavedPassenger::setFirstName,SavedPassenger::getFirstName);
        UtilHelper.updateIfNotNull(oldSavedPassenger::setLastName,SavedPassenger::getLastName);
        return mapSavedPassengerEntityToDto(SavedPassengerRepository.save(oldSavedPassenger));
    }

    @Override
    public void deleteSavedPassengerByUser(String username, Long id) {
        SavedPassengerRepository.delete(getSavedPassengerByIdAndUser(username,id));
    }



    public SavedPassengerService(@Autowired SavedPassengerRepository SavedPassengerRepository) {
        this.SavedPassengerRepository = SavedPassengerRepository;
    }
}
