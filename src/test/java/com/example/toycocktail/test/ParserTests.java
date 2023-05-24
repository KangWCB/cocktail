package com.example.toycocktail.test;

import com.example.toycocktail.data.CocktailParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
public class ParserTests {
    @Autowired
    private CocktailParser cocktailParser;

    @Test
    public void parserTest() throws Exception {
        System.out.println("hi");
        cocktailParser.read();
    }
}
