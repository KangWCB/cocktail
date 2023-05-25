package com.example.toycocktail.test;

import com.example.toycocktail.data.CocktailParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class ParserTests {
    @Autowired
    private CocktailParser cocktailParser;

    @Test
    @Rollback(false)
    public void parserTest() throws Exception {
        System.out.println("hi");
        cocktailParser.read();
    }
}
