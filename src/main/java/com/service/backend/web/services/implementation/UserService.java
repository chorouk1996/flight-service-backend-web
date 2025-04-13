package com.service.backend.web.services.implementation;

import com.service.backend.web.models.dto.UserDto;
import com.service.backend.web.models.entities.User;
import com.service.backend.web.repositories.UserRepository;
import com.service.backend.web.services.helper.UserServiceHelper;
import com.service.backend.web.services.inetrface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.service.backend.web.services.helper.UserServiceHelper.*;

import java.util.List;



@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto addUser(UserDto user) {

         return mapUserEntityToDto(userRepository.save(mapUserDtoToEntity(user)));
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map(pass->mapUserEntityToDto(pass)).toList();
    }


}
