package com.service.backend.web.repositories;

import com.service.backend.web.models.entities.Passenger;
import com.service.backend.web.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {

    List<Passenger> findByUser(User user);
    Optional<Passenger> findByUserAndId(User user, Long id);
}
