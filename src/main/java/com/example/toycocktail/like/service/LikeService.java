package com.example.toycocktail.like.service;

import com.example.toycocktail.cocktail.constant.CocktailConstant;
import com.example.toycocktail.cocktail.model.Cocktail;
import com.example.toycocktail.cocktail.repository.CocktailRepository;
import com.example.toycocktail.like.model.Likes;
import com.example.toycocktail.like.repository.LikesRepository;
import com.example.toycocktail.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final LikesRepository likesRepository;
    private final CocktailRepository cocktailRepository;

    @Transactional
    public Long likeCocktail(Member member, Long cocktailId){
        Cocktail cocktail = cocktailRepository.findById(cocktailId).orElseThrow(() -> new IllegalArgumentException(CocktailConstant.NOT_FOUND_COCKTAIL));
        Likes like = Likes.builder()
                .cocktail(cocktail)
                .member(member)
                .build();

        Likes savedLike = likesRepository.save(like);
        return savedLike.getId();
    }
}
