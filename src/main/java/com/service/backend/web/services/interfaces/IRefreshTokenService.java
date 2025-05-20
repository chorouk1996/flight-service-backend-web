package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.entities.User;


public interface IRefreshTokenService {

    void saveRefreshToken(String token, User user);

     void disableAllTokens(User user);

    boolean isTokenNotValid(String token);

}
