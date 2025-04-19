package com.service.backend.web.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.backend.web.exceptions.FunctionalException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (FunctionalException exception) {
            if (!response.isCommitted()) {
                var dto = exception.getFunctionalExceptionDto();
                dto.setPath(request.getRequestURI());
                response.setStatus(dto.getStatus().value());
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getWriter(), dto);
            }
        }
    }
}

