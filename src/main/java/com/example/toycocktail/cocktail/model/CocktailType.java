package com.example.toycocktail.cocktail.model;

public enum CocktailType {
    LONG("롱"), SHORT("숏");
    private final String type;

    CocktailType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
