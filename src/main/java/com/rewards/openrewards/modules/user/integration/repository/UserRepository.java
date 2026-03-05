package com.rewards.openrewards.modules.user.integration.repository;

import com.rewards.openrewards.modules.user.integration.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
}
