package com.example.toycocktail.member.service;


import com.example.toycocktail.member.model.Member;
import com.example.toycocktail.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(Member member){
        memberRepository.findByEmail(member.getEmail())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
        memberRepository.save(member);
        return member.getId();
    }

}
