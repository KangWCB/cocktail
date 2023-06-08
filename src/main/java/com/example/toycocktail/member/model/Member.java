package com.example.toycocktail.member.model;


import com.example.toycocktail.member.constant.Role;
import com.example.toycocktail.member.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String intro;

    @Enumerated(EnumType.STRING)
    private Role role; // enum???

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .email(memberFormDto.getEmail())
                .name(memberFormDto.getName())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .role(Role.USER)
                .build();
        return member;
    }
}
