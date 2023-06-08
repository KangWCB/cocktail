package com.example.toycocktail.like.service;

import com.example.toycocktail.cocktail.constant.CocktailConstant;
import com.example.toycocktail.cocktail.model.Cocktail;
import com.example.toycocktail.cocktail.repository.CocktailRepository;
import com.example.toycocktail.like.dto.LikesCocktailResponse;
import com.example.toycocktail.like.model.Likes;
import com.example.toycocktail.like.repository.LikesRepository;
import com.example.toycocktail.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final LikesRepository likesRepository;
    private final CocktailRepository cocktailRepository;

    @Transactional
    public Long likeCocktail(Member member, Long cocktailId){
        Cocktail cocktail = cocktailRepository.findById(cocktailId).orElseThrow(() -> new NoSuchElementException(CocktailConstant.NOT_FOUND_COCKTAIL));
        boolean exist = likesRepository.existsLikesByCocktailAndMember(cocktail, member);
        if (!exist) {
            Likes like = Likes.builder()
                    .cocktail(cocktail)
                    .member(member)
                    .build();

            Likes savedLike = likesRepository.save(like);
            return savedLike.getId();

        } else {
            Likes findLikes = likesRepository.findByCocktailAndMember(cocktail, member);
            likesRepository.delete(findLikes);
            return findLikes.getId();
        }
    }

    // 좋아요 여부 확인
    public boolean isLikeCocktail(Long cocktailId, Member member){
        Cocktail cocktail = cocktailRepository.findById(cocktailId).orElseThrow(() -> new NoSuchElementException(CocktailConstant.NOT_FOUND_COCKTAIL));
        return likesRepository.existsLikesByCocktailAndMember(cocktail, member);
    }

    // 멤버가 좋아한 칵테일 가져오기
    public List<LikesCocktailResponse> getLikesCocktailList(Member member){
        List<Cocktail> likesCocktail = likesRepository.findLikesCocktail(member);
        return likesCocktail.stream().map(LikesCocktailResponse::cocktailToDto).toList();
    }
}
