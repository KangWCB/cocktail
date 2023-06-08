package com.example.toycocktail.member.dto;


import lombok.Data;

@Data
public class TokenInfoDto {
    private String accessToken;
    private String refreshToken;
}
