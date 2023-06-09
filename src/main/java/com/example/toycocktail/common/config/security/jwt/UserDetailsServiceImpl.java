package com.example.toycocktail.common.config.security.jwt;

import com.example.toycocktail.member.model.Member;
import com.example.toycocktail.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException { // memberId값 사용
        Member findMember = memberRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디의 유저는 존재하지 않습니다."));
        if (findMember != null)
        {
            UserDetails userDetails = new UserDetails(findMember);
            return userDetails;
        }
        return null;
    }
}
