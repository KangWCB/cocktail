package com.example.toycocktail.cocktail.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Liquid {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private float alcoholLevel;

    private String description;

    private String company;

    private int price;

    @Enumerated(EnumType.STRING)
    private Category category;
}
