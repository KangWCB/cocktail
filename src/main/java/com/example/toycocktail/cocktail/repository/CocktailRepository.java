package com.example.toycocktail.cocktail.repository;

import com.example.toycocktail.cocktail.model.Cocktail;
import com.example.toycocktail.cocktail.repository.query.CocktailRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail,Long>, CocktailRepositoryCustom {
    Optional<Cocktail> findByName(String name);
}
