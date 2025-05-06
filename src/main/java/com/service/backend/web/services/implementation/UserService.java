package com.service.backend.web.services.implementation;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.requests.AuthentUserRequest;
import com.service.backend.web.models.requests.CreateUserRequest;
import com.service.backend.web.models.requests.PasswordUpdateRequest;
import com.service.backend.web.models.responses.AuthenticationResponse;
import com.service.backend.web.models.responses.CreateUserResponse;
import com.service.backend.web.models.entities.User;
import com.service.backend.web.models.responses.UserPaginationResponse;
import com.service.backend.web.repositories.UserRepository;
import com.service.backend.web.services.interfaces.IUserService;
import com.service.backend.web.services.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.service.backend.web.services.mapper.UserMapper.mapCreateUserRequestToEntity;
import static com.service.backend.web.services.mapper.UserMapper.mapEntityToCreateUserResponse;


@Service
public class UserService implements IUserService {

    private static final String USER_NOT_FOUND = "User Not Found";
    UserRepository userRepository;

    JwtService jwtService;
    AuthenticationManager manager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public CreateUserResponse addUser(CreateUserRequest user) {

        if (doesUserExist(user.getEmail()))
            throw new FunctionalException(new FunctionalExceptionDto("This User already exist", HttpStatus.CONFLICT));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return mapEntityToCreateUserResponse(userRepository.save(mapCreateUserRequestToEntity(user)));
    }

    @Override
    public List<CreateUserResponse> getAllUser() {
        return userRepository.findAll().stream().map(UserMapper::mapEntityToCreateUserResponse).toList();
    }

    @Override
    public UserPaginationResponse getAllUser(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        UserPaginationResponse result = new UserPaginationResponse();
        result.setUsers(userRepository.findAll(pageable).stream().map(UserMapper::mapEntityToCreateUserResponse).toList());
        result.setPage(page);
        result.setSize(size);
        return result;
    }

    public boolean doesUserExist(String email) {
        return getUser(email).isPresent();
    }

    @Override
    public String authenticate(AuthentUserRequest user) {
        Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        try {

            if (auth.isAuthenticated()) {
                return jwtService.generateToken(user.getEmail());
            }
        } catch (BadCredentialsException badCredentialsException) {
            return "Bad Credentials";
        }
        return null;
    }

    @Override
    public String refreshToken(AuthenticationResponse authenticationRequest) {

        return jwtService.refreshToken(authenticationRequest.getToken());
    }

    @Override
    public void updatePassword(PasswordUpdateRequest user) {
        User currentUser = getUser(user.getEmail()).orElseThrow(
                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto(USER_NOT_FOUND, HttpStatus.UNAUTHORIZED));
                }
        );

        if (bCryptPasswordEncoder.matches(user.getOldPassword(), currentUser.getPassword())) {
            currentUser.setPassword(bCryptPasswordEncoder.encode(user.getNewPassword()));
        } else {
            throw new FunctionalException(new FunctionalExceptionDto(USER_NOT_FOUND, HttpStatus.UNAUTHORIZED));
        }
        userRepository.save(currentUser);
    }

    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);

    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto(USER_NOT_FOUND, HttpStatus.UNAUTHORIZED));
                }
        );

    }
    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto(USER_NOT_FOUND, HttpStatus.UNAUTHORIZED));
                }
        );

    }

    @Override
    public void blockUser(long userId) {
       User user = getUserById(userId);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public void unBlockUser(long userId) {
        User user = getUserById(userId);
        user.setEnabled(false);
        userRepository.save(user);    }


    public UserService(UserRepository userRepository, AuthenticationManager manager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.manager = manager;
        this.jwtService = jwtService;
    }
}
