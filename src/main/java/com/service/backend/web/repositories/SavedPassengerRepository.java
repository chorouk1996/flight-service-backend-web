package com.service.backend.web.repositories;

import com.service.backend.web.models.entities.SavedPassenger;
import com.service.backend.web.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavedPassengerRepository extends JpaRepository<SavedPassenger,Long> {

    List<SavedPassenger> findByUser(User user);
    Optional<SavedPassenger> findByUserAndId(User user, Long id);
}
