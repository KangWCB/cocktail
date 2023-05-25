package com.example.toycocktail.common.config.db;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostgresSQLConfig implements ApplicationRunner {

    private final DataSource dataSource;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()){
            log.info("[init] dataSource Class > " + dataSource.getClass());
            log.info("[init] > URL > " + connection.getMetaData().getURL());
            log.info("[init] > userName > " + connection.getMetaData().getUserName());

        }catch (Exception e){
            log.error("DB ERROR: NOT INIT, {}", e.getMessage());
            throw e;
        }
    }
}
