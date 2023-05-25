package com.example.toycocktail.cocktail.model;


import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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

    private String amount;

}
