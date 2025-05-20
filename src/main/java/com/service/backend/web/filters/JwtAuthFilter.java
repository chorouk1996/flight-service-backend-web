package com.service.backend.web.filters;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.security.UserDetailsServiceImpl;
import com.service.backend.web.services.implementation.JwtService;
import com.service.backend.web.services.interfaces.IRefreshTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    IRefreshTokenService refreshTokenService;

    @Autowired
    UserDetailsServiceImpl userDetailsImpl;
    String username = null;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authToken = request.getHeader("Authorization");
        if (authToken != null && authToken.startsWith("Bearer ")) {
            String token = authToken.substring(7);
            try {
                username = jwtService.extractUsername(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null && jwtService.isValid(token)) {
                    UserDetails user = userDetailsImpl.loadUserByUsername(username);
                    Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);

                }
            }
            catch(FunctionalException exception){
                exception.getFunctionalExceptionDto().setPath(request.getRequestURI());
                throw exception;
            }


        }
        filterChain.doFilter(request, response);
    }
}
