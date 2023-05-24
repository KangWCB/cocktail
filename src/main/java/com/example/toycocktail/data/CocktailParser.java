package com.example.toycocktail.data;

import com.example.toycocktail.cocktail.model.Cocktail;
import com.example.toycocktail.cocktail.repository.CocktailRepository;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Component
public class CocktailParser {

    private final CocktailRepository cocktailRepository;

    public void read() throws IOException, CsvException {
        InputStream in = getClass().getResourceAsStream("/data.csv");
        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> data = new ArrayList<String[]>();
        data = csvReader.readAll();
        //List<Cocktail> cocktailList = new ArrayList<Cocktail>();
        // idx 9 ~ 23 ingredient
//        data.forEach(line ->
//                log.info("data: {}", String.join(",",line))
//        );

        List<CocktailData> cocktailDataList = new CsvToBeanBuilder<CocktailData>(reader)
                .withType(CocktailData.class)
                .build().parse();
        System.out.println(cocktailDataList);
        cocktailDataList.forEach(cocktailData -> log.info("name: {}", cocktailData.getName()));

    }
}
