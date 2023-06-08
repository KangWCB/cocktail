package com.example.toycocktail.like.repository;

import com.example.toycocktail.cocktail.model.Cocktail;
import com.example.toycocktail.like.model.Likes;
import com.example.toycocktail.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Long>, CustomLikesRepository {

    boolean existsLikesByCocktailAndMember(Cocktail cocktail, Member member);

    Likes findByCocktailAndMember(Cocktail cocktail, Member member);

}
