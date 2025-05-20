package com.service.backend.web.models.dto;


import com.service.backend.web.models.enumerators.TypeTokenEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
public class EmailTokenDto {


    private Long id;
    private String token;

    private TypeTokenEnum type;

    private LocalDateTime expireAt;

    private LocalDateTime createdAt;

    private String email;

    private boolean used;

    private String ipAddress;



}
