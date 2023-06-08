package com.example.toycocktail.common.config.security.jwt.dto;


import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Builder
@Getter
@RedisHash(value = "refreshToken", timeToLive = 3600 * 24 * 15) // TTL = 초단위 적용 15일
public class RefreshToken {

    @Id
    private String id;

    @Indexed
    private String refreshTokenValue;

}
