package com.service.backend.web.repositories;

import com.service.backend.web.models.entities.RefreshToken;
import com.service.backend.web.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    List<RefreshToken> findByUser(User user);

    Optional<RefreshToken> findByTokenAndExpired(String token, boolean expired);
}
