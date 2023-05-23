package com.example.toycocktail.member.model;


import com.example.toycocktail.member.constant.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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
}
