package com.example.toycocktail.data;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;

@Getter
public class CocktailData {
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvBindByName(column = "strInstructions")
    private String description;
    @CsvBindByName(column = "strAlcoholic")
    private String alcoholic; // Alcohol, non
    @CsvBindByName(column = "strCategory")
    private String category;
    @CsvBindByName(column = "strDrinkThumb")
    private String imgUrl;
    @CsvBindByName(column = "strGlass")
    private String glass;

}
