package com.rewards.openrewards.modules.auth.integration.repository;

import com.rewards.openrewards.modules.auth.integration.entity.AuthCredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthCredentialsRepository extends JpaRepository<AuthCredentialsEntity, Long> {
    Optional<AuthCredentialsEntity> findByEmail(String email);
}
