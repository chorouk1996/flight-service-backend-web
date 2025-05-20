package com.service.backend.web.security;

import com.service.backend.web.constantes.ErrorMessages;
import com.service.backend.web.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username).map(
                UserDetailsImpl::new).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException(ErrorMessages.USER_NOT_FOUND);
                });

    }
}
