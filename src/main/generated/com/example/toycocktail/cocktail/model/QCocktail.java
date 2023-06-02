package com.example.toycocktail.cocktail.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCocktail is a Querydsl query type for Cocktail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCocktail extends EntityPathBase<Cocktail> {

    private static final long serialVersionUID = -1065255274L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCocktail cocktail = new QCocktail("cocktail");

    public final EnumPath<Alcoholic> alcoholic = createEnum("alcoholic", Alcoholic.class);

    public final StringPath category = createString("category");

    public final StringPath description = createString("description");

    public final StringPath glass = createString("glass");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final ListPath<com.example.toycocktail.like.model.Likes, com.example.toycocktail.like.model.QLikes> likesList = this.<com.example.toycocktail.like.model.Likes, com.example.toycocktail.like.model.QLikes>createList("likesList", com.example.toycocktail.like.model.Likes.class, com.example.toycocktail.like.model.QLikes.class, PathInits.DIRECT2);

    public final com.example.toycocktail.member.model.QMember member;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> views = createNumber("views", Integer.class);

    public QCocktail(String variable) {
        this(Cocktail.class, forVariable(variable), INITS);
    }

    public QCocktail(Path<? extends Cocktail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCocktail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCocktail(PathMetadata metadata, PathInits inits) {
        this(Cocktail.class, metadata, inits);
    }

    public QCocktail(Class<? extends Cocktail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.toycocktail.member.model.QMember(forProperty("member")) : null;
    }

}

