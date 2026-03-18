package com.rewards.openrewards.modules.auth.integration.repository;

import com.rewards.openrewards.modules.auth.integration.entity.AuthCredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCredentialsRepository extends JpaRepository<AuthCredentialsEntity, Long> {

}
