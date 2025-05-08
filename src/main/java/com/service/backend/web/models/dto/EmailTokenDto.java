package com.service.backend.web.models.dto;


import com.service.backend.web.models.enumerators.TypeTokenEnum;

import java.time.LocalDateTime;

public class EmailTokenDto {


    private Long id;
    private String token;

    private TypeTokenEnum type;

    private LocalDateTime expireAt;

    private LocalDateTime createdAt;

    private String email;

    private boolean used;

    private String ipAddress;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TypeTokenEnum getType() {
        return type;
    }

    public void setType(TypeTokenEnum type) {
        this.type = type;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
