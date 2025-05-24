package com.service.backend.web.services.implementation;

import com.service.backend.web.models.dto.RefreshTokenDto;
import com.service.backend.web.models.entities.RefreshToken;
import com.service.backend.web.models.entities.User;
import com.service.backend.web.repositories.RefreshTokenRepository;
import com.service.backend.web.services.interfaces.IRefreshTokenService;
import com.service.backend.web.services.mapper.RefreshTokenMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class RefreshTokenService implements IRefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    public void saveRefreshToken(String token, User user) {
        RefreshTokenDto dto = new RefreshTokenDto();
        dto.setCreatedAT(System.currentTimeMillis());
        dto.setExpiredAT(System.currentTimeMillis() + 1000 * 60 * 60);
        dto.setHashToken(digestToken(token));
        dto.setExpired(Boolean.FALSE);
        dto.setUser(user);
        disableAllTokens(user);
        refreshTokenRepository.save(RefreshTokenMapper.mapRefreshTokenDtoToEntity(dto));

    }

    @Override
    public void disableAllTokens(User user) {
       List<RefreshToken> tokens =  refreshTokenRepository.findByUser(user);
        tokens.forEach(refreshToken -> refreshToken.setExpired(Boolean.TRUE));
        refreshTokenRepository.saveAll(tokens);
    }

    @Override
    public boolean isTokenNotValid(String token) {
        return refreshTokenRepository.findByHashTokenAndExpired(token,true).isPresent();
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private String digestToken(String token){
        try {
            MessageDigest hashAlgo = MessageDigest.getInstance("SHA256");
            return bytesToHex(hashAlgo.digest(token.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
