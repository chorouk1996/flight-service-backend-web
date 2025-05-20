package com.service.backend.web.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.backend.web.exceptions.FunctionalException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@AllArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

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
                objectMapper.writeValue(response.getWriter(), dto);
            }
        }
    }
}

