package com.example.toycocktail.cocktail.model;


import com.example.toycocktail.member.model.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;

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

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Alcoholic alcoholic; // Alcohol, non

    private String category;

    private String imgUrl;

    private String glass;

    private int views;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 초기데이터
    public static Cocktail createForInit(String name,String description,Alcoholic alcoholic,String category,String imgUrl,String glass){
        return Cocktail.builder()
                .name(name)
                .description(description)
                .alcoholic(alcoholic)
                .category(category)
                .imgUrl(imgUrl)
                .views(0)
                .glass(glass).build();
    }

    // 사용자가 추가
    public static Cocktail create(String name,String description,Alcoholic alcoholic,String category,String imgUrl,String glass,Member member){
        Cocktail cocktail = createForInit(name, description, alcoholic, category, imgUrl, glass);
        cocktail.member = member;
        return cocktail;
    }
}
