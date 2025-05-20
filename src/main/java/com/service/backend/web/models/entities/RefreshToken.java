package com.service.backend.web.models.entities;


import jakarta.persistence.*;


@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String hashToken;

    @Column
    private long createdAT;

    @Column
    private long expiredAT;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
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
