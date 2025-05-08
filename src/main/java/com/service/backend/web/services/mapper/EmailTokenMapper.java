package com.service.backend.web.services.mapper;

import com.service.backend.web.models.dto.EmailTokenDto;
import com.service.backend.web.models.entities.EmailToken;

public class EmailTokenMapper {


    private EmailTokenMapper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    }


    public static EmailToken mapDtoToEntity(EmailTokenDto dto) {
        EmailToken entity = new EmailToken();
        entity.setEmail(dto.getEmail());
        entity.setToken(dto.getToken());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setExpireAt(dto.getExpireAt());
        entity.setType(dto.getType());
        entity.setUsed(Boolean.FALSE);
        entity.setIpAddress(dto.getIpAddress());
        return entity;
    }

    public static EmailTokenDto mapEntityToDto(EmailToken entity) {
        EmailTokenDto dto = new EmailTokenDto();
        dto.setEmail(entity.getEmail());
        dto.setToken(entity.getToken());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setExpireAt(entity.getExpireAt());
        dto.setType(entity.getType());
        dto.setUsed(Boolean.FALSE);
        dto.setIpAddress(entity.getIpAddress());
        return dto;
    }
}
