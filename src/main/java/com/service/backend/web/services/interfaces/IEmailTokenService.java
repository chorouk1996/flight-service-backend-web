package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.EmailTokenDto;
import com.service.backend.web.models.entities.EmailToken;
import com.service.backend.web.models.requests.ResetPasswordRequest;

public interface IEmailTokenService {


    void addEmailToken(EmailTokenDto emailToken);

    EmailToken isResetTokenValid(ResetPasswordRequest resetToken);


    void deleteExpiredUnusedTokens();

}
