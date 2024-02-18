package ru.bzvs.higharc.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DBConfig {

//    @Bean
//    public NamedParameterJdbcTemplate slaveTemplate() {
//        DataSourceBuilder builder = DataSourceBuilder.create();
//        builder.driverClassName("org.postgresql.Driver");
//        builder.url("jdbc:postgresql://localhost:15432/users");
//        builder.username("postgres");
//        builder.password("slava");
//        return new NamedParameterJdbcTemplate(builder.build());
//    }

    @Bean
    public NamedParameterJdbcTemplate masterTemplate() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.driverClassName("org.postgresql.Driver");
        builder.url("jdbc:postgresql://localhost:5432/higharc");
        builder.username("postgres");
        builder.password("slava");
        return new NamedParameterJdbcTemplate(builder.build());
    }
}
