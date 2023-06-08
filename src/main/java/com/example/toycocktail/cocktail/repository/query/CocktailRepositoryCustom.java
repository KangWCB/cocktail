package com.example.toycocktail.cocktail.repository.query;

import com.example.toycocktail.cocktail.dto.SearchCond;
import com.example.toycocktail.cocktail.model.Cocktail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CocktailRepositoryCustom {

    /**
     * 기본 정렬은 조회수를 기준으로 한다.(익명일때)
     */
    Page<Cocktail> findBySearchCond(SearchCond searchCond, Pageable pageable);
    /**
     * 멤버가 존재할때 좋아요된 항목을 우선으로 보여주고 조회수 정렬
     */
    Page<Cocktail> findBySearchCond(SearchCond searchCond, Pageable pageable, Long memberId);
}
