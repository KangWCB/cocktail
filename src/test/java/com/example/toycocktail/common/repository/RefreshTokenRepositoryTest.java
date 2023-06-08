package com.example.toycocktail.common.repository;

import com.example.toycocktail.common.config.security.jwt.dto.RefreshToken;
import com.example.toycocktail.common.config.security.jwt.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class RefreshTokenRepositoryTest {

    @Autowired
    RefreshTokenRepository repository;
    @Test
    void findByRefreshToken() {
    }
    @Test
    void 저장() {
        RefreshToken refreshToken = RefreshToken.builder()
                .id("id1")
                .refreshTokenValue("abc")
                .build();
        repository.save(refreshToken);
    }


}