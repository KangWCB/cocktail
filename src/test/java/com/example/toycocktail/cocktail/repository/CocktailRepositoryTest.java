package com.example.toycocktail.cocktail.repository;

import com.example.toycocktail.cocktail.model.Cocktail;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CocktailRepositoryTest {

    @Autowired
    CocktailRepository repository;

    @Autowired
    EntityManager em;

    @Test
    void save(){
        Cocktail cocktail = Cocktail.builder()
                .name("잭콕")
                .glass("롱")
                .alcoholic("알코올").build();
        repository.save(cocktail);

        em.flush();
        em.clear();

        Cocktail findCocktail = repository.findByName("잭콕")
                .orElseThrow(() -> new IllegalArgumentException("해당 칵테일 없음"));

        Assertions.assertThat(cocktail.getGlass()).isEqualTo(findCocktail.getGlass());
    }
}