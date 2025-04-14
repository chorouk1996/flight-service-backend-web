package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.UserDto;

import java.util.List;


public interface IUserService {

    public UserDto addUser(UserDto user);

    public List<UserDto>getAllUser();

}
