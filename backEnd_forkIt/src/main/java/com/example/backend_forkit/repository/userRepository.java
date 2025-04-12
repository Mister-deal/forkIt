package com.example.backend_forkit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface userRepository  extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.active = true ")
    Optional<User> findActiveUserByEmail(@Param("email") String email);

}
