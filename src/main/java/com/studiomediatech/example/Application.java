package com.studiomediatech.example;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Configuration
    public static class Config {

        @Bean
        @ConditionalOnMissingBean
        JdbcTemplate jdbcTemplate(HikariDataSource clusterDataSource) {

            return new JdbcTemplate(clusterDataSource);
        }


        @Bean
        @Primary
        @ConfigurationProperties("app.datasource.cluster")
        DataSourceProperties clusterDataSourceProps() {

            return new DataSourceProperties();
        }


        @Bean
        @ConfigurationProperties("app.datasource.cluster.configuration")
        HikariDataSource clusterDataSource(DataSourceProperties clusterDataSourceProps) {

            return clusterDataSourceProps.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        }


        @Bean
        @ConfigurationProperties("app.datasource.archive")
        DataSourceProperties archiveDataSourceProps() {

            return new DataSourceProperties();
        }


        @Bean
        @ConfigurationProperties("app.datasource.archive.configuration")
        HikariDataSource archiveDataSource(DataSourceProperties archiveDataSourceProps) {

            return archiveDataSourceProps.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        }
    }
}
