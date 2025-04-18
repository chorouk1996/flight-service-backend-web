package com.service.backend.web.services.mapper;

import com.service.backend.web.models.dto.UserDto;
import com.service.backend.web.models.dto.requests.CreateUserRequest;
import com.service.backend.web.models.dto.responses.CreateUserResponse;
import com.service.backend.web.models.entities.User;
import com.service.backend.web.models.enumerators.RoleEnum;


public final class UserMapper {

    private UserMapper()  {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    }

    public static UserDto mapUserEntityToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    public static User mapCreateUserRequestToEntity(CreateUserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setRole(RoleEnum.valueOf(userRequest.getRole()));
        return user;
    }

    public static CreateUserResponse mapEntityToCreateUserResponse(User user) {
        CreateUserResponse userResponse = new CreateUserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole().name());
        return userResponse;
    }

    public static User mapUserDtoToEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        return user;
    }


}
