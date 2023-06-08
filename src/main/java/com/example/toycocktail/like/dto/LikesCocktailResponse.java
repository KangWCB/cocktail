package com.example.toycocktail.like.dto;

import com.example.toycocktail.cocktail.dto.SearchResponse;

import com.example.toycocktail.cocktail.model.Cocktail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@Builder
public class LikesCocktailResponse {
    private Long id;
    private String name;
    private String imgUrl;
    private String drinkTypeName;

    public static LikesCocktailResponse cocktailToDto(Cocktail cocktail){
       return LikesCocktailResponse.builder()
                .id(cocktail.getId())
                .name(cocktail.getName())
                .imgUrl(cocktail.getImgUrl())
                .drinkTypeName(cocktail.getAlcoholic().name())
                .build();
    }

}
