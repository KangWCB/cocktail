package com.example.toycocktail.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberFormDto {

    @Email
    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotEmpty(message = "닉네임을 입력해주세요.")
    private String name;

    @Builder
    public MemberFormDto(String email, String password, String name)
    {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}

