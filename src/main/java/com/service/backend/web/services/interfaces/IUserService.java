package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.requests.AuthentUserRequest;
import com.service.backend.web.models.requests.CreateUserRequest;
import com.service.backend.web.models.requests.PasswordUpdateRequest;
import com.service.backend.web.models.responses.AuthenticationResponse;
import com.service.backend.web.models.responses.CreateUserResponse;
import com.service.backend.web.models.entities.User;

import java.util.List;


public interface IUserService {

     CreateUserResponse addUser(CreateUserRequest user);

     List<CreateUserResponse>getAllUser();


    String authenticate(AuthentUserRequest user);

    String refreshToken(AuthenticationResponse token);

    void updatePassword(PasswordUpdateRequest user);

    User getUserById(String email);
}
