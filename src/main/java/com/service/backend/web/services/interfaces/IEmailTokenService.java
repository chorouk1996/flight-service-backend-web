package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.EmailTokenDto;

public interface IEmailTokenService {


    void addEmailToken(EmailTokenDto emailToken);
}
