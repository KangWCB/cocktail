package com.example.toycocktail.like.repository;

import com.example.toycocktail.cocktail.model.Cocktail;
import com.example.toycocktail.member.model.Member;

import java.util.List;

public interface CustomLikesRepository {
    List<Cocktail> findLikesCocktail(Member member);
}
