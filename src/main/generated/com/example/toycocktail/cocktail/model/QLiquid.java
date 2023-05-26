package com.example.toycocktail.cocktail.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLiquid is a Querydsl query type for Liquid
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLiquid extends EntityPathBase<Liquid> {

    private static final long serialVersionUID = -65878994L;

    public static final QLiquid liquid = new QLiquid("liquid");

    public final NumberPath<Float> alcoholLevel = createNumber("alcoholLevel", Float.class);

    public final StringPath category = createString("category");

    public final StringPath company = createString("company");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public QLiquid(String variable) {
        super(Liquid.class, forVariable(variable));
    }

    public QLiquid(Path<? extends Liquid> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLiquid(PathMetadata metadata) {
        super(Liquid.class, metadata);
    }

}

