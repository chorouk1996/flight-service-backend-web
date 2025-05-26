package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.requests.*;
import com.service.backend.web.models.responses.AuthenticationResponse;
import com.service.backend.web.models.responses.CreateUserResponse;
import com.service.backend.web.models.entities.User;
import com.service.backend.web.models.responses.RefreshTokenResponse;
import com.service.backend.web.models.responses.UserPaginationResponse;
import com.service.backend.web.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


public interface IUserService {

     CreateUserResponse addUser(CreateUserRequest user);

     List<CreateUserResponse>getAllUsers();

    UserPaginationResponse getAllUsers(int page, int size);
    AuthenticationResponse authenticate(AuthentUserRequest user);

    void logout(String refreshToken);

    RefreshTokenResponse refreshToken(String token);

    void resetToken(ResetTokenRequest tokenRequest, HttpServletRequest request);

    void resetPassword(ResetPasswordRequest request);

    void updatePassword(PasswordUpdateRequest user, UserDetailsImpl username);

    User getUserByEmail(String email);

    boolean isUserExistAndActive(String email);

    User getUserById(long id);

    void blockUser(long id);

    void unBlockUser(long id);

    void updatePasswordAfterReset(ResetPasswordRequest request, User user);

}
