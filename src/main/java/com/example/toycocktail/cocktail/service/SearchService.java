package com.example.toycocktail.cocktail.service;

import com.example.toycocktail.cocktail.dto.SearchCond;
import com.example.toycocktail.cocktail.dto.SearchResponse;
import com.example.toycocktail.cocktail.model.Cocktail;
import com.example.toycocktail.cocktail.repository.CocktailRepository;
import com.example.toycocktail.cocktail.repository.LiquidRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class SearchService {
    private final CocktailRepository cocktailRepository;
    private final LiquidRepository liquidRepository;

    /**
     * 기본은 무조건 Cocktail
     */
    public Page<SearchResponse> search(SearchCond searchCond, Pageable pageable){
        String type = searchCond.getDrinkTypeName();
        if (type.equals("Cocktail")){
            Page<Cocktail> findCocktailList = cocktailRepository.findBySearchCond(searchCond, pageable);
            return SearchResponse.cocktailToDtoList(findCocktailList);
        }
//        if (type.equals("Liquid")){
//            return null; // 작성 예정
//        }
        return null;
    }

}
