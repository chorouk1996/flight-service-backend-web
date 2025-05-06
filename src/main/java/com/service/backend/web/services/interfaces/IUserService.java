package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.requests.AuthentUserRequest;
import com.service.backend.web.models.requests.CreateUserRequest;
import com.service.backend.web.models.requests.PasswordUpdateRequest;
import com.service.backend.web.models.responses.AuthenticationResponse;
import com.service.backend.web.models.responses.CreateUserResponse;
import com.service.backend.web.models.entities.User;
import com.service.backend.web.models.responses.UserPaginationResponse;

import java.util.List;


public interface IUserService {

     CreateUserResponse addUser(CreateUserRequest user);

     List<CreateUserResponse>getAllUsers();

    UserPaginationResponse getAllUsers(int page, int size);
    String authenticate(AuthentUserRequest user);

    String refreshToken(AuthenticationResponse token);

    void updatePassword(PasswordUpdateRequest user);

    User getUserByEmail(String email);

    User getUserById(long email);

    void blockUser(long user);

    void unBlockUser(long user);

}
