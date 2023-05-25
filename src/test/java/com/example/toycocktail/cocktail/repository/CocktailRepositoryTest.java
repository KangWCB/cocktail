package com.example.toycocktail.cocktail.repository;

import com.example.toycocktail.cocktail.dto.SearchCond;
import com.example.toycocktail.cocktail.model.Alcoholic;
import com.example.toycocktail.cocktail.model.Cocktail;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
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
                .alcoholic(Alcoholic.BASIC).build();
        repository.save(cocktail);

        em.flush();
        em.clear();

        Cocktail findCocktail = repository.findByName("잭콕")
                .orElseThrow(() -> new IllegalArgumentException("해당 칵테일 없음"));

        assertThat(cocktail.getGlass()).isEqualTo(findCocktail.getGlass());
    }

    @Test
    void findByNameAndGet5(){
        SearchCond sc = SearchCond.builder()
                .name("Absolut")
                .isAlcoholic(true)
                .build();

        PageRequest pageRequest = PageRequest.of(0,5);

        Page<Cocktail> findCocktails = repository.findBySearchCond(sc, pageRequest);

        List<Cocktail> content = findCocktails.getContent();
//        content.stream().forEach(v -> log.info("result = {}", v.getName()));

        content.stream().forEach(v -> {
            assertThat(v.getAlcoholic()).isEqualTo(Alcoholic.BASIC);
        });

        assertThat(content.size()).isEqualTo(5);
    }

    @Test
    void findByAlcoholicBy10(){
        SearchCond sc = SearchCond.builder()
                .isAlcoholic(true)
                .build();

        PageRequest pageRequest = PageRequest.of(0,10);

        Page<Cocktail> findCocktails = repository.findBySearchCond(sc, pageRequest);

        List<Cocktail> content = findCocktails.getContent();
//        content.stream().forEach(v -> log.info("result = {}", v.getName()));

        content.stream().forEach(v -> {
            assertThat(v.getAlcoholic()).isEqualTo(Alcoholic.BASIC);
        });
        assertThat(content.size()).isEqualTo(10);
    }
}