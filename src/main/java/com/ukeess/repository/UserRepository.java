package com.ukeess.repository;

import com.ukeess.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
public interface UserRepository extends JpaRepository<AuthUser, Integer> {
    Optional<AuthUser> findByName(String name);
}
