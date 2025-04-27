package com.service.backend.web.services.implementation;

import com.service.backend.web.models.entities.User;
import com.service.backend.web.models.enumerators.BookingStatusEnum;
import com.service.backend.web.repositories.PassengerRepository;
import com.service.backend.web.services.interfaces.IPassengerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PassengerService implements IPassengerService {
    PassengerRepository passengerRepository;


}
