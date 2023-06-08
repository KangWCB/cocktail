package com.example.toycocktail.like.service;

import com.example.toycocktail.cocktail.model.Cocktail;
import com.example.toycocktail.cocktail.repository.CocktailRepository;
import com.example.toycocktail.like.repository.LikesRepository;
import com.example.toycocktail.member.model.Member;
import com.example.toycocktail.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LikeServiceTest {

    @Autowired
    LikeService likeService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CocktailRepository cocktailRepository;
    @Autowired
    EntityManager em;

    @Autowired
    LikesRepository likesRepository;

    @Test
    void like(){
        Member member = memberRepository.findById(1L).orElseThrow(IllegalStateException::new);
        Long likesId = likeService.likeCocktail(member, 3L);
        em.flush();
        em.clear();
        assertThat(likesRepository.count()).isEqualTo(1);
        assertThat(likesRepository.findAll().stream().findFirst().orElseThrow(IllegalStateException::new).getId()).isEqualTo(likesId);
    }

    @Test
    void likes_delete(){
        Member member = memberRepository.findById(1L).orElseThrow(IllegalStateException::new);
        Long likesId = likeService.likeCocktail(member, 3L);
        Long deleteId = likeService.likeCocktail(member, 3L);
        em.flush();
        em.clear();
        assertThat(likesRepository.count()).isEqualTo(0);
        assertThatThrownBy(()->likesRepository.findAll().stream().findFirst().orElseThrow(IllegalStateException::new).getId()).isInstanceOf(IllegalStateException.class);
    }

}