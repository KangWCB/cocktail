package com.example.toycocktail.cocktail.repository;

import com.example.toycocktail.cocktail.model.Cocktail;
import com.example.toycocktail.cocktail.model.Liquid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LiquidRepository extends JpaRepository<Liquid, Long> {
    Optional<Liquid> findByName(String name);
}
