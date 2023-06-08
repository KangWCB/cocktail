package com.example.toycocktail.like.repository;

import com.example.toycocktail.cocktail.model.Cocktail;
import com.example.toycocktail.cocktail.repository.CocktailRepository;
import com.example.toycocktail.like.model.Likes;
import com.example.toycocktail.member.model.Member;
import com.example.toycocktail.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LikesRepositoryTest {

    @Autowired
    LikesRepository likesRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CocktailRepository cocktailRepository;

    @Autowired
    EntityManager em;

    @Test
    void check_like_list(){
        Member member = memberRepository.findById(1L).orElseThrow(IllegalStateException::new);

        Cocktail cocktail = cocktailRepository.findById(3L).orElseThrow(IllegalStateException::new);
        Likes like = Likes.builder()
                .cocktail(cocktail)
                .member(member)
                .build();
        Likes savedLike = likesRepository.save(like);

        em.flush();
        em.clear();

        List<Cocktail> likesCocktail = likesRepository.findLikesCocktail(member);
        assertThat(likesCocktail.stream().map(c -> c.getName()).collect(Collectors.toList())).contains(cocktail.getName());
        assertThat(likesCocktail.size()).isEqualTo(1);
    }
}