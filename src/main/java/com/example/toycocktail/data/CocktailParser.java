package com.example.toycocktail.data;

import com.example.toycocktail.cocktail.model.Cocktail;
import com.example.toycocktail.cocktail.model.InnerLiquid;
import com.example.toycocktail.cocktail.model.Liquid;
import com.example.toycocktail.cocktail.repository.CocktailRepository;
import com.example.toycocktail.cocktail.repository.InnerLiquidRepository;
import com.example.toycocktail.cocktail.repository.LiquidRepository;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MultiValuedMap;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.mysema.commons.lang.Assert.assertThat;


@Slf4j
@RequiredArgsConstructor
@Component
public class CocktailParser {

    private final CocktailRepository cocktailRepository;
    private final LiquidRepository liquidRepository;
    private final InnerLiquidRepository innerLiquidRepository;

    public void read() throws IOException, CsvException {
        InputStream in = getClass().getResourceAsStream("/data.csv");
        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);

        // cocktail
        List<CocktailData> cocktailDataList = new CsvToBeanBuilder<CocktailData>(new CSVReader(reader))
                .withType(CocktailData.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build().parse();

        //초기화
        MultiValuedMap<String,String> liquids;
        MultiValuedMap<String,String> amounts;

        for(CocktailData cocktailData: cocktailDataList)
        {
            HashMap<String,String> liquidMap = new HashMap<>();
            liquids = cocktailData.getLiquids();
            amounts = cocktailData.getAmounts();
            for (int i=0;i<16;i++) // 컬럼 인덱스 9 ~ 15 ingredient
            {
                String liquidName = liquids.get("strIngredient"+i).toString().replaceAll("[\\[\\]]","");
                String liquidAmount = amounts.get("strMeasure"+i).toString().replaceAll("[\\[\\]]","");
                if(!liquidName.isEmpty() && liquidName != null)
                    liquidMap.put(liquidName,liquidAmount);
            }
            // Cocktail check
            Cocktail cocktail = cocktailRepository.findByName(cocktailData.getName())
                    .orElseGet(() -> cocktailRepository.save(
                            Cocktail.builder()
                                    .name(cocktailData.getName())
                                    .description(cocktailData.getDescription())
                                    .alcoholic(cocktailData.getAlcoholic())
                                    .category(cocktailData.getCategory())
                                    .imgUrl(cocktailData.getImgUrl())
                                    .glass(cocktailData.getGlass())
                                    .build()
                    ));

            for(String liquidName: liquidMap.keySet()) {
                Liquid liquid = liquidRepository.findByName(liquidName)
                        .orElseGet(() -> liquidRepository.save(
                                Liquid.builder()
                                        .name(liquidName)
                                        .build()
                        ));
                if(!innerLiquidRepository.existsByLiquidAndCocktail(liquid,cocktail))
                {
                    innerLiquidRepository.save(
                            InnerLiquid.builder()
                                    .amount(liquidMap.get(liquidName))
                                    .cocktail(cocktail)
                                    .liquid(liquid)
                                    .build()
                    );
                }
            }


        }




        // idx 9 ~ 23 ingredient





    }
}
