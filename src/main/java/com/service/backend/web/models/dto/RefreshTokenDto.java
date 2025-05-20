package com.service.backend.web.models.dto;


import com.service.backend.web.models.entities.User;

import java.time.LocalDateTime;

public class RefreshTokenDto {


    private long id;

    private String hashToken;

    private long createdAT;

    private long expiredAT;


    private User user;

    private Boolean expired;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHashToken() {
        return hashToken;
    }

    public void setHashToken(String hashToken) {
        this.hashToken = hashToken;
    }

    public long getCreatedAT() {
        return createdAT;
    }

    public void setCreatedAT(long createdAT) {
        this.createdAT = createdAT;
    }

    public long getExpiredAT() {
        return expiredAT;
    }

    public void setExpiredAT(long expiredAT) {
        this.expiredAT = expiredAT;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }
}
