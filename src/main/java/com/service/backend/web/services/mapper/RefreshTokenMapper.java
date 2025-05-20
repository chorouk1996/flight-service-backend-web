package com.service.backend.web.services.mapper;

import com.service.backend.web.models.dto.RefreshTokenDto;
import com.service.backend.web.models.entities.RefreshToken;

public class RefreshTokenMapper {

    private RefreshTokenMapper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    }
    public static RefreshTokenDto mapRefreshTokenEntityToDto(RefreshToken refreshToken) {
        RefreshTokenDto dto = new RefreshTokenDto();
        dto.setId(refreshToken.getId());
        dto.setHashToken(refreshToken.getHashToken());
        dto.setCreatedAT(refreshToken.getCreatedAT());
        dto.setExpiredAT(refreshToken.getExpiredAT());
        dto.setUser(refreshToken.getUser());
        return dto;
    }


    public static RefreshToken mapRefreshTokenDtoToEntity(RefreshTokenDto dto) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setId(dto.getId());
        refreshToken.setHashToken(dto.getHashToken());
        refreshToken.setCreatedAT(dto.getCreatedAT());
        refreshToken.setExpiredAT(dto.getExpiredAT());
        refreshToken.setUser(dto.getUser());
        return refreshToken;
    }



}
