package com.example.toycocktail.cocktail.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInnerLiquid is a Querydsl query type for InnerLiquid
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInnerLiquid extends EntityPathBase<InnerLiquid> {

    private static final long serialVersionUID = 143144832L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInnerLiquid innerLiquid = new QInnerLiquid("innerLiquid");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final QCocktail cocktail;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLiquid liquid;

    public QInnerLiquid(String variable) {
        this(InnerLiquid.class, forVariable(variable), INITS);
    }

    public QInnerLiquid(Path<? extends InnerLiquid> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInnerLiquid(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInnerLiquid(PathMetadata metadata, PathInits inits) {
        this(InnerLiquid.class, metadata, inits);
    }

    public QInnerLiquid(Class<? extends InnerLiquid> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cocktail = inits.isInitialized("cocktail") ? new QCocktail(forProperty("cocktail"), inits.get("cocktail")) : null;
        this.liquid = inits.isInitialized("liquid") ? new QLiquid(forProperty("liquid")) : null;
    }

}

