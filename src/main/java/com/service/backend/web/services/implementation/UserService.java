package com.service.backend.web.services.implementation;

import com.service.backend.web.models.dto.requests.AuthentUserRequest;
import com.service.backend.web.models.dto.requests.CreateUserRequest;
import com.service.backend.web.models.dto.responses.CreateUserResponse;
import com.service.backend.web.repositories.UserRepository;
import com.service.backend.web.services.interfaces.IUserService;
import com.service.backend.web.services.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import static com.service.backend.web.services.mapper.UserMapper.*;
import static com.service.backend.web.services.mapper.UserMapper.mapEntityToCreateUserResponse;

import java.security.NoSuchAlgorithmException;
import java.util.List;



@Service
public class UserService implements IUserService {

    UserRepository userRepository;

    JwtService jwtService;
    AuthenticationManager manager;
    @Override
    public CreateUserResponse addUser(CreateUserRequest user) {
        user.setPassword(new BCryptPasswordEncoder(10).encode(user.getPassword()));
         return mapEntityToCreateUserResponse(userRepository.save(mapCreateUserRequestToEntity(user)));
    }

    @Override
    public List<CreateUserResponse> getAllUser() {
        return userRepository.findAll().stream().map(UserMapper::mapEntityToCreateUserResponse).toList();
    }

    @Override
    public String authenticate(AuthentUserRequest user) throws NoSuchAlgorithmException {
        Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        try {

            if(auth.isAuthenticated()){
                return jwtService.generateToken(user.getEmail());
            }
        }
        catch(BadCredentialsException badCredentialsException){
            return "Bad Credentials";
        }
        return null;
    }

    public UserService(UserRepository userRepository, AuthenticationManager manager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.manager = manager;
        this.jwtService = jwtService;
    }
}
