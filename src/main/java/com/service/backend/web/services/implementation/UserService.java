package com.service.backend.web.services.implementation;

import com.service.backend.web.constantes.ErrorMessages;
import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.dto.EmailTokenDto;
import com.service.backend.web.models.entities.EmailToken;
import com.service.backend.web.models.entities.User;
import com.service.backend.web.models.enumerators.TypeTokenEnum;
import com.service.backend.web.models.requests.*;
import com.service.backend.web.models.responses.AuthenticationResponse;
import com.service.backend.web.models.responses.CreateUserResponse;
import com.service.backend.web.models.responses.RefreshTokenResponse;
import com.service.backend.web.models.responses.UserPaginationResponse;
import com.service.backend.web.repositories.UserRepository;
import com.service.backend.web.security.UserDetailsImpl;
import com.service.backend.web.services.interfaces.IRefreshTokenService;
import com.service.backend.web.services.interfaces.IUserService;
import com.service.backend.web.services.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.service.backend.web.services.mapper.UserMapper.mapCreateUserRequestToEntity;
import static com.service.backend.web.services.mapper.UserMapper.mapEntityToCreateUserResponse;


@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final JwtService jwtService;
    private final AuthenticationManager manager;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final EmailTokenService emailTokenService;

    private final IRefreshTokenService refreshTokenService;

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public CreateUserResponse addUser(CreateUserRequest user) {

        if (doesUserExist(user.getEmail()))
            throw new FunctionalException(new FunctionalExceptionDto("This User already exist", HttpStatus.CONFLICT));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return mapEntityToCreateUserResponse(userRepository.save(mapCreateUserRequestToEntity(user)));
    }

    @Override
    public List<CreateUserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::mapEntityToCreateUserResponse).toList();
    }

    @Override
    public UserPaginationResponse getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        UserPaginationResponse result = new UserPaginationResponse();
        result.setUsers(userRepository.findAll(pageable).stream().map(UserMapper::mapEntityToCreateUserResponse).toList());
        result.setPage(page);
        result.setSize(size);
        result.setTotalElements(userRepository.count());
        return result;
    }

    public boolean doesUserExist(String email) {
        return getUser(email).isPresent();
    }

    @Override
    public AuthenticationResponse authenticate(AuthentUserRequest user) {
        Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        try {
            if (auth.isAuthenticated()) {
                String token = jwtService.generateToken(user.getEmail());
                String refreshToken = jwtService.generateRefreshToken(user.getEmail());
                AuthenticationResponse response = new AuthenticationResponse(token, refreshToken);
                redisTemplate.opsForValue().set("refresh_token:" + refreshToken, user.getEmail(), Duration.ofDays(30));
                return response;
            }
        } catch (BadCredentialsException badCredentialsException) {
            throw new FunctionalException(new FunctionalExceptionDto("Bad Credentials", HttpStatus.BAD_REQUEST));
        }
        return null;
    }

    @Override
    public RefreshTokenResponse refreshToken(String token) {
        String userEmail = redisTemplate.opsForValue().get("refresh_token:" + token);
        if (StringUtils.hasText(userEmail))
            if (userEmail.equals(jwtService.extractUsername(token))) {
                return new RefreshTokenResponse(jwtService.generateToken(userEmail));
            }
        throw new FunctionalException(new FunctionalExceptionDto("You should signin", HttpStatus.UNAUTHORIZED));

    }

    @Override
    public void logout(String refreshToken) {
        redisTemplate.delete("refresh_token:" + refreshToken);
    }

   /* @Override
    public String refreshToken(String authenticationRequest) {
        User user = getUserByEmail(jwtService.extractUsername(authenticationRequest));
        if (refreshTokenService.isTokenNotValid(authenticationRequest)) {
            refreshTokenService.disableAllTokens(user);
            throw new FunctionalException(new FunctionalExceptionDto("You need to login to generate a new token",HttpStatus.UNAUTHORIZED));
        }
        String token = jwtService.refreshToken(authenticationRequest);
        refreshTokenService.saveRefreshToken(token, user);
        return token;
    }*/


    @Override
    public void resetToken(ResetTokenRequest tokenRequest, HttpServletRequest request) {
        isUserExistAndActive(tokenRequest.getEmail());
        String token = jwtService.generateResetToken(tokenRequest.getEmail());
        EmailTokenDto dto = new EmailTokenDto();
        dto.setCreatedAt(LocalDateTime.now());
        dto.setExpireAt(LocalDateTime.now().plusMinutes(15));
        dto.setToken(token);
        dto.setType(TypeTokenEnum.RESET_TOKEN);
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        dto.setIpAddress(ipAddress);
        dto.setEmail(tokenRequest.getEmail());
        dto.setUsed(Boolean.FALSE);
        emailTokenService.addEmailToken(dto);
        System.out.println("this is the reset-link https://yourdomain.com/reset-password?token=" + token);
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {
        EmailToken token = emailTokenService.isResetTokenValid(request);
        Optional<User> opUser = userRepository.findByEmailAndEnabled(token.getEmail(), Boolean.TRUE);
        if (opUser.isPresent()) {
            User user = opUser.get();
            updatePasswordAfterReset(request, user);
            emailTokenService.setTokenUsed(token);
            refreshTokenService.disableAllTokens(user);
        }

    }

    @Override
    public void updatePassword(PasswordUpdateRequest request, UserDetailsImpl user) {
        User currentUser = getUser(request.getEmail()).orElseThrow(() -> {
            throw new FunctionalException(new FunctionalExceptionDto(ErrorMessages.USER_NOT_FOUND, HttpStatus.UNAUTHORIZED));
        });

        if (bCryptPasswordEncoder.matches(request.getOldPassword(), currentUser.getPassword()) && bCryptPasswordEncoder.matches(user.getPassword(), currentUser.getPassword()) && user.getUsername().equalsIgnoreCase(request.getEmail())) {
            currentUser.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        } else {
            throw new FunctionalException(new FunctionalExceptionDto(ErrorMessages.USER_NOT_FOUND, HttpStatus.UNAUTHORIZED));
        }
        userRepository.save(currentUser);
    }

    @Override
    public void updatePasswordAfterReset(ResetPasswordRequest request, User user) {
        user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);

    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> {
            throw new FunctionalException(new FunctionalExceptionDto(ErrorMessages.USER_NOT_FOUND, HttpStatus.UNAUTHORIZED));
        });

    }


    @Override
    public User isUserExistAndActive(String email) {
        return userRepository.findByEmailAndEnabled(email, true).orElseThrow(() -> {
            throw new FunctionalException(new FunctionalExceptionDto(ErrorMessages.USER_NOT_FOUND, HttpStatus.UNAUTHORIZED));
        });
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new FunctionalException(new FunctionalExceptionDto(ErrorMessages.USER_NOT_FOUND, HttpStatus.UNAUTHORIZED));
        });

    }

    @Override
    @Transactional
    public void blockUser(long userId) {
        User user = getUserById(userId);
        user.setEnabled(false);
        userRepository.save(user);
        refreshTokenService.disableAllTokens(user);
    }

    @Override
    @Transactional
    public void unBlockUser(long userId) {
        User user = getUserById(userId);
        user.setEnabled(true);
        userRepository.save(user);
    }


}
