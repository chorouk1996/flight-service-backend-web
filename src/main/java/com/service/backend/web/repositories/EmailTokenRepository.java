package com.service.backend.web.repositories;

import com.service.backend.web.models.entities.EmailToken;
import com.service.backend.web.models.enumerators.TypeTokenEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailTokenRepository extends JpaRepository<EmailToken, Long> {

    Optional<EmailToken> findByTokenAndUsedAndExpireAtAfterAndType(String token, boolean used, LocalDateTime time, TypeTokenEnum type);

    void deleteByUsedAndExpireAtBeforeAndType(boolean used, LocalDateTime time, TypeTokenEnum type);
}
