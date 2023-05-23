package com.example.toycocktail.cocktail.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class InnerLiquid {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "liquid_id")
    private Liquid liquid;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktail;

    private int amount;

}
