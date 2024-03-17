package ru.bzvs.higharc.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@EnableJpaRepositories(basePackages = "ru.bzvs.higharc.repository")
@Configuration
public class DBConfig {
}
