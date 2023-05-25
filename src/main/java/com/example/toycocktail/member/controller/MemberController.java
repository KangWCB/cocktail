package com.example.toycocktail.member.controller;


import com.example.toycocktail.member.dto.MemberFormDto;
import com.example.toycocktail.member.model.Member;
import com.example.toycocktail.member.service.MemberService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public void login() {
        log.info("call login");
    }



    @PostMapping("/join")
    public void join(@RequestBody MemberFormDto memberFormDto) {
        memberService.join(Member.createMember(memberFormDto, passwordEncoder));
    }

}
