package com.service.backend.web.services.implementation;

import com.service.backend.web.models.dto.EmailTokenDto;
import com.service.backend.web.repositories.EmailTokenRepository;
import com.service.backend.web.services.interfaces.IEmailTokenService;
import com.service.backend.web.services.mapper.EmailTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailTokenService implements IEmailTokenService {

    @Autowired
    EmailTokenRepository emailTokenRepository;


    @Override
    public void addEmailToken(EmailTokenDto emailToken) {
        emailTokenRepository.save(EmailTokenMapper.mapDtoToEntity(emailToken));
    }
}
