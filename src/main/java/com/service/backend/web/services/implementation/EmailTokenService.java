package com.service.backend.web.services.implementation;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.dto.EmailTokenDto;
import com.service.backend.web.models.entities.EmailToken;
import com.service.backend.web.models.enumerators.TypeTokenEnum;
import com.service.backend.web.models.requests.ResetPasswordRequest;
import com.service.backend.web.repositories.EmailTokenRepository;
import com.service.backend.web.services.interfaces.IEmailTokenService;
import com.service.backend.web.services.mapper.EmailTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmailTokenService implements IEmailTokenService {

    @Autowired
    EmailTokenRepository emailTokenRepository;


    @Override
    public void addEmailToken(EmailTokenDto emailToken) {
        emailTokenRepository.save(EmailTokenMapper.mapDtoToEntity(emailToken));
    }


    @Override
    public EmailToken isResetTokenValid(ResetPasswordRequest resetToken) {
        Optional<EmailToken> emailToken = emailTokenRepository.findByTokenAndUsedAndExpireAtAfterAndType(resetToken.getToken(), Boolean.FALSE, LocalDateTime.now(), TypeTokenEnum.RESET_TOKEN);
        EmailToken token = emailToken.orElseThrow(
                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto("Your token is not valid", HttpStatus.NOT_FOUND));
                }
        );
        token.setUsed(Boolean.FALSE);
        emailTokenRepository.save(token);
        return token;
    }

    @Override
    public void deleteExpiredUnusedTokens() {
        emailTokenRepository.deleteByUsedAndExpireAtAfterAndType(Boolean.FALSE, LocalDateTime.now(), TypeTokenEnum.RESET_TOKEN);
    }
}
