package com.example.toycocktail.member.controller;


import com.example.toycocktail.common.config.security.jwt.JwtTokenProvider;
import com.example.toycocktail.common.config.security.jwt.dto.TokenInfo;
import com.example.toycocktail.member.dto.MemberFormDto;
import com.example.toycocktail.member.dto.MemberLoginDto;
import com.example.toycocktail.member.dto.TokenInfoDto;
import com.example.toycocktail.member.model.Member;
import com.example.toycocktail.member.repository.MemberRepository;
import com.example.toycocktail.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginDto memberLoginDto) {
        log.info("call login: {} {}",memberLoginDto.getEmail(),memberLoginDto.getPassword());
        Member member = memberRepository.findByEmail(memberLoginDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 이메일 입니다.")); // 나중에 공통 exception 으로 변경해야함
        if(!passwordEncoder.matches(memberLoginDto.getPassword(), member.getPassword()))
        {
            throw new IllegalStateException("이메일 혹은 비밀번호가 올바르지 않습니다."); // 추후 수정
        }
        return jwtTokenProvider.createToken(member.getId().toString());
    }



    @PostMapping("/join")
    public void join(@RequestBody MemberFormDto memberFormDto) {
        memberService.join(Member.createMember(memberFormDto, passwordEncoder));
    }

    @PostMapping("/reissue")
    public ResponseEntity reissue(@RequestBody TokenInfoDto tokenInfoDto) {
        String newAccessToken = jwtTokenProvider.reissueToken(tokenInfoDto.getAccessToken(), tokenInfoDto.getRefreshToken());
        if (newAccessToken == null)
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(newAccessToken, HttpStatus.OK);
    }

}
