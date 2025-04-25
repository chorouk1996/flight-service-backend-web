package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.requests.AuthentUserRequest;
import com.service.backend.web.models.dto.requests.CreateUserRequest;
import com.service.backend.web.models.dto.requests.PasswordUpdateRequest;
import com.service.backend.web.models.dto.responses.AuthenticationResponse;
import com.service.backend.web.models.dto.responses.CreateUserResponse;

import java.util.List;


public interface IUserService {

     CreateUserResponse addUser(CreateUserRequest user);

     List<CreateUserResponse>getAllUser();


    String authenticate(AuthentUserRequest user);

    String refreshToken(AuthenticationResponse token);

    void updatePassword(PasswordUpdateRequest user);


}
