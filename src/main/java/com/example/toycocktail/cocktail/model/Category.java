package com.example.toycocktail.cocktail.model;

public enum Category {
    VODKA("보드카"),RUM("럼"), TEQUILA("데킬라"), WHISKEY("위스키");

    private final String name;
    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
