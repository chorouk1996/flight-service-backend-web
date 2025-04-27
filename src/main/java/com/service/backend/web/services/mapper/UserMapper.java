package com.service.backend.web.services.mapper;

import com.service.backend.web.models.dto.UserDto;
import com.service.backend.web.models.requests.CreateUserRequest;
import com.service.backend.web.models.responses.CreateUserResponse;
import com.service.backend.web.models.entities.User;
import com.service.backend.web.models.enumerators.RoleEnum;


public final class UserMapper {

    private UserMapper()  {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    }

    public static UserDto mapUserEntityToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    public static User mapCreateUserRequestToEntity(CreateUserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setRole(RoleEnum.valueOf(userRequest.getRole()));
        return user;
    }

    public static CreateUserResponse mapEntityToCreateUserResponse(User user) {
        CreateUserResponse userResponse = new CreateUserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole().name());
        return userResponse;
    }

    public static User mapUserDtoToEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        return user;
    }


}
