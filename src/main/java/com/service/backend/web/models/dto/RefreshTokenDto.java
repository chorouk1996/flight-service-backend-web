package com.service.backend.web.models.dto;


import com.service.backend.web.models.entities.User;

import lombok.Data;


@Data
public class RefreshTokenDto {


    private long id;

    private String hashToken;

    private long createdAT;

    private long expiredAT;


    private User user;

    private Boolean expired;


}
