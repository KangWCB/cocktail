package com.example.toycocktail.cocktail.model;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Alcoholic {
    BASIC("Alcoholic"),NON("Non alcoholic"),OPTIONAL("Optional alcohol");

    private String label;

    Alcoholic(String label) {
        this.label = label;
    }
    public String label() {
        return label;
    }

    private static final Map<String, Alcoholic> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(Alcoholic::label, e -> e));

    public static Alcoholic valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
