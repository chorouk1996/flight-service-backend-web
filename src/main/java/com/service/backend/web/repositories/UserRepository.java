package com.service.backend.web.repositories;

import com.service.backend.web.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByEmail(String username);

    Optional<User> findByEmailAndActive(String username,boolean active);

}
