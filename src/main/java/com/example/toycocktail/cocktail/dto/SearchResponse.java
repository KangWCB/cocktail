package com.example.toycocktail.cocktail.dto;

import com.example.toycocktail.cocktail.model.Cocktail;
import com.example.toycocktail.cocktail.model.Liquid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@Builder
public class SearchResponse {
    private Long id;
    private String name;
    private String imgUrl;
    private String drinkTypeName;
    private int views;

    public static Page<SearchResponse> cocktailToDtoList(Page<Cocktail> cocktailList){
        Page<SearchResponse> boardDtoList = cocktailList.map(m -> SearchResponse.builder()
                .id(m.getId())
                .name(m.getName())
                .imgUrl(m.getImgUrl())
                .drinkTypeName(m.getAlcoholic().label())
                .views(m.getViews())
                .build());
        return boardDtoList;
    }
    public static Page<SearchResponse> liquidToDtoList(Page<Liquid> cocktailList){
        Page<SearchResponse> boardDtoList = cocktailList.map(m -> SearchResponse.builder()
                .id(m.getId())
                .name(m.getName())
                .imgUrl("")
                .drinkTypeName("요소")
                .views(m.getViews())
                .build());
        return boardDtoList;
    }
}
