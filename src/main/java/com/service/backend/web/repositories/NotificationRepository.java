package com.service.backend.web.repositories;

import com.service.backend.web.models.entities.Notification;
import com.service.backend.web.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {


    List<Notification> findByUser(User user);

    Optional<Notification> findByIdAndUser(Long id,User user);


    void deleteByCreatedAtBefore(LocalDateTime date);
}
