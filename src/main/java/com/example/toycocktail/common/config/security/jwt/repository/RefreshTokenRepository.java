package com.example.toycocktail.common.config.security.jwt.repository;

import com.example.toycocktail.common.config.security.jwt.dto.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshTokenValue(String refreshTokenValue);
}
