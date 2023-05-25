package com.example.toycocktail.cocktail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCond {

    private String name; // 이름
    private String drinkTypeName = "Cocktail"; // 칵테일을 찾는 건지 재료를 찾는건지 아무것도 입력이 없을시 기본 칵테일
    private Boolean isAlcoholic;

}
