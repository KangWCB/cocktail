package com.example.toycocktail.data;

import com.example.toycocktail.cocktail.model.Alcoholic;
import com.example.toycocktail.cocktail.model.Cocktail;
import com.example.toycocktail.cocktail.model.InnerLiquid;
import com.example.toycocktail.cocktail.model.Liquid;
import com.example.toycocktail.cocktail.repository.CocktailRepository;
import com.example.toycocktail.cocktail.repository.InnerLiquidRepository;
import com.example.toycocktail.cocktail.repository.LiquidRepository;
import com.example.toycocktail.member.model.Member;
import com.example.toycocktail.member.repository.MemberRepository;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MultiValuedMap;

import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;



@Slf4j
@RequiredArgsConstructor
@Component
@ConditionalOnResource(resources = "/data.csv")
@Profile("init")
public class CocktailParser {

    private final CocktailRepository cocktailRepository;
    private final LiquidRepository liquidRepository;
    private final InnerLiquidRepository innerLiquidRepository;
    private final String resource = "/data.csv";

    private final MemberRepository memberRepository;

    @PostConstruct
    public void initData(){
        try{
            read();
            createSuperMember();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    /**
     * 임시 슈퍼 계정 생성
     */
    @Transactional
    public void createSuperMember(){
        Member user = Member.builder()
                .email("cocktail@naver.com")
                .password("1234")
                .intro("안녕하세요.").build();
        memberRepository.save(user);
    } 

    @Transactional(rollbackFor = Exception.class)
    public void read() throws Exception {
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
            Alcoholic alcoholic = Alcoholic.valueOfLabel(cocktailData.getAlcoholic());
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
                            Cocktail.createForInit(cocktailData.getName(),
                                    cocktailData.getDescription(),
                                    alcoholic,
                                    cocktailData.getCategory(),
                                    cocktailData.getImgUrl(),
                                    cocktailData.getGlass())
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
    }

}
