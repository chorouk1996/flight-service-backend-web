package com.service.backend.web.repositories;

import com.service.backend.web.models.entities.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailTokenRepository extends JpaRepository<EmailToken,Long> {


}
