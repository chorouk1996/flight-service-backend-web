package com.service.backend.web.services.implementation;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.dto.requests.CreatePassengerRequest;
import com.service.backend.web.models.dto.requests.UpdatePassengerRequest;
import com.service.backend.web.models.entities.Passenger;
import com.service.backend.web.repositories.PassengerRepository;
import com.service.backend.web.services.helper.UtilHelper;
import com.service.backend.web.services.interfaces.IPassengerService;
import com.service.backend.web.services.mapper.PassengerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.service.backend.web.services.mapper.PassengerMapper.*;

@Service
public class PassengerService implements IPassengerService {

    PassengerRepository passengerRepository;

    @Autowired UserService userService;
    @Override
    public PassengerDto addPassenger(CreatePassengerRequest passenger,String username) {
        Passenger newPassenger = mapCreatePassengerRequestToEntity(passenger);
        newPassenger.setUser(userService.getUserById(username));

        return  mapPassengerEntityToDto(passengerRepository.save(newPassenger));
    }


    @Override
    public List<PassengerDto> getAllPassenger(String username) {
        return passengerRepository.findByUser(userService.getUserById(username)).stream().map(PassengerMapper::mapPassengerEntityToDto).toList();
    }

    @Override
    public PassengerDto getPassengerById(String username, Long id) {
        return mapPassengerEntityToDto(getPassengerByIdAndUser(username,id));
    }

    private Passenger getPassengerByIdAndUser(String username, Long id) {
        return passengerRepository.findByUserAndId(userService.getUserById(username),id).orElseThrow(
                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto("The passenger doesn't exist or don't belong to this user", HttpStatus.NOT_FOUND));
                }
        );

    }
    @Override
    public PassengerDto updatePassengerByUser(String username, UpdatePassengerRequest passenger, Long id) {
        Passenger oldPassenger = getPassengerByIdAndUser(username,id);
        UtilHelper.updateIfNotNull(oldPassenger::setAge,passenger::getAge);
        UtilHelper.updateIfNotNull(oldPassenger::setEmail,passenger::getEmail);
        UtilHelper.updateIfNotNull(oldPassenger::setFirstName,passenger::getFirstName);
        UtilHelper.updateIfNotNull(oldPassenger::setLastName,passenger::getLastName);
        return mapPassengerEntityToDto(passengerRepository.save(oldPassenger));
    }

    @Override
    public void deletePassengerByUser(String username, Long id) {
        passengerRepository.delete(getPassengerByIdAndUser(username,id));
    }


    @Override
    public List<Passenger> addAllPassenger(List<CreatePassengerRequest> passengers) {
        return passengerRepository.saveAll(passengers.stream().map(PassengerMapper::mapCreatePassengerRequestToEntity).toList());
    }

    public PassengerService(@Autowired PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }
}
