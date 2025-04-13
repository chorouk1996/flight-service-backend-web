package com.service.backend.web.services.inetrface;

import com.service.backend.web.models.dto.UserDto;
import com.service.backend.web.models.entities.User;

import java.util.List;


public interface IUserService {

    public UserDto addUser(UserDto user);

    public List<UserDto>getAllUser();

}
