package com.example.toycocktail.cocktail.model;


import com.example.toycocktail.member.model.Member;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Cocktail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String alcoholic; // Alcohol, non

    private String category;

    private String imgUrl;

    private String glass;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Cocktail initData(String name,String description,String alcoholic,String category,String imgUrl,String glass){
        return Cocktail.builder()
                .name(name)
                .description(description)
                .alcoholic(alcoholic)
                .category(category)
                .imgUrl(imgUrl)
                .glass(glass).build();
    }
}
