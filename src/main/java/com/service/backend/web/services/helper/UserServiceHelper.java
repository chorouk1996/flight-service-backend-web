package com.service.backend.web.services.helper;

import com.service.backend.web.models.dto.UserDto;
import com.service.backend.web.models.entities.User;
import org.springframework.stereotype.Service;


@Service
public class UserServiceHelper  {

    public static UserDto mapUserEntityToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
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
