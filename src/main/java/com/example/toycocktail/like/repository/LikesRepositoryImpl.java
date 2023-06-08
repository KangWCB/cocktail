package com.example.toycocktail.like.repository;

import com.example.toycocktail.cocktail.model.Cocktail;
import com.example.toycocktail.cocktail.model.QCocktail;
import com.example.toycocktail.like.model.Likes;
import com.example.toycocktail.like.model.QLikes;
import com.example.toycocktail.member.model.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LikesRepositoryImpl implements CustomLikesRepository{

    private final JPAQueryFactory query;


    QCocktail qCocktail = QCocktail.cocktail;
    QLikes qLikes = QLikes.likes;
    @Override
    public List<Cocktail> findLikesCocktail(Member member) {
        List<Likes> findLikes = query.select(qLikes)
                .from(qLikes)
                .leftJoin(qLikes.cocktail, qCocktail).fetchJoin()
                .where(qLikes.member.eq(member))
                .fetch();

        List<Cocktail> result = findLikes.stream().map(likes -> likes.getCocktail()).toList();

        return result;

    }
}
