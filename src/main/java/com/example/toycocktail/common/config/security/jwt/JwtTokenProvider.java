package com.example.toycocktail.common.config.security.jwt;


import com.example.toycocktail.common.config.security.jwt.dto.RefreshToken;
import com.example.toycocktail.common.config.security.jwt.dto.TokenInfo;
import com.example.toycocktail.common.config.security.jwt.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    @Value("${spring.jwt.secret}")
    private String secretKey;
    private final Long accessTokenValidTime = 3600 * 3 * 1000L; // 3시간
    private final Long refreshTokenValidTime = 3600 * 24 * 15 * 1000L; // 15일

    private final UserDetailsServiceImpl userDetailsService;

    private final RefreshTokenRepository refreshTokenRepository;
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public TokenInfo createToken(String userId) {
        log.info("secretkey: {}",secretKey);

        String accessToken = createAccessToken(userId);
        String refreshToken = createRefreshToken(userId);

        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public boolean validateRefreshToken(String accessToken, String refreshToken) {
        String tokenUserId = getUsername(accessToken);
        RefreshToken targetRefreshToken = refreshTokenRepository.findByRefreshTokenValue(refreshToken)
                .orElseThrow(() -> new NullPointerException("해당 토큰이 redis에 존재하지 않습니다."));

        if (!tokenUserId.equals(targetRefreshToken.getId())) { // accessToken id, redis의 refreshToken id 값이 동일하지않을때
            log.info("잘못된 토큰 정보입니다.");
            return false;
        }
        return true;
    }

    public String reissueToken(String accessToken, String refreshToken) {
        boolean refreshTokenValid = validateRefreshToken(accessToken, refreshToken);
        if (!refreshTokenValid)
        {
            return null;
        }
        return createAccessToken(getUsername(refreshToken));
    }
    public String createRefreshToken(String userId) {
        Date now = new Date();
        Date refreshTokenValidity = new Date(now.getTime() + refreshTokenValidTime);

        String refreshTokenValue = Jwts.builder()
                .setExpiration(refreshTokenValidity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        RefreshToken refreshToken = RefreshToken.builder()
                .id(userId)
                .refreshTokenValue(refreshTokenValue)
                .build();

        refreshTokenRepository.save(refreshToken);

        return refreshTokenValue;
    }

    public String createAccessToken(String userId) {
        Claims claims = Jwts.claims().setSubject(userId); // 유니크한 값 설정
        Date now = new Date();
        Date accessTokenValidity = new Date(now.getTime() + accessTokenValidTime);

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(accessTokenValidity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return accessToken;
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }



}
