package com.service.backend.web.models.responses;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RefreshTokenResponse {

    @NotBlank
    private String token;

}
