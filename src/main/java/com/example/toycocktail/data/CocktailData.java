package com.example.toycocktail.data;


import com.opencsv.bean.CsvBindAndJoinByName;
import com.opencsv.bean.CsvBindAndJoinByPositions;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.List;

@Getter
public class CocktailData {
    @CsvBindByName(column = "strDrink")
    private String name;
    //@CsvBindByName(column = "strInstructions")
    private String description;
    @CsvBindByName(column = "strAlcoholic")
    private String alcoholic; // Alcohol, non
    @CsvBindByName(column = "strCategory")
    private String category;
    @CsvBindByName(column = "strDrinkThumb")
    private String imgUrl;
    @CsvBindByName(column = "strGlass")
    private String glass;
    @CsvBindAndJoinByName(column = "strIngredient[1-15]+", elementType = String.class)
    private MultiValuedMap<String,String> liquids;

    @CsvBindAndJoinByName(column = "strMeasure[1-15]+", elementType = String.class)
    private MultiValuedMap<String,String> amounts;
}
