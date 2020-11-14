package com.studiomediatech.example;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;

import org.springframework.core.io.ResourceLoader;

import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;

import java.nio.file.Files;

import javax.sql.DataSource;


@SpringBootApplication
public class Application {

    @Autowired
    ClusterJdbcTemplate clusterJdbcTemplate;

    @Autowired
    ArchiveJdbcTemplate archiveJdbcTemplate;

    @Autowired
    ResourceLoader resourceLoader;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }


    @EventListener
    void on(ApplicationReadyEvent event) {

        try {
            clusterJdbcTemplate.execute(Files.readString(
                    resourceLoader.getResource("classpath:schema-cluster.sql").getFile().toPath()));
            archiveJdbcTemplate.execute(Files.readString(
                    resourceLoader.getResource("classpath:schema-archive.sql").getFile().toPath()));
        } catch (RuntimeException | IOException ex) {
            throw new FatalBeanException("Failed to initialize schema", ex);
        }
    }

    @Configuration
    public static class Config {

        @Bean
        @Primary
        ClusterJdbcTemplate clusterJdbcTemplate(HikariDataSource clusterDataSource) {

            return new ClusterJdbcTemplate(clusterDataSource);
        }


        @Bean
        ArchiveJdbcTemplate archiveJdbcTemplate(HikariDataSource archiveDataSource) {

            return new ArchiveJdbcTemplate(archiveDataSource);
        }


        @Bean
        @Primary
        ClusterDataSourceProperties clusterDataSourceProps() {

            return new ClusterDataSourceProperties();
        }


        @Bean
        ArchiveDataSourceProperties archiveDataSourceProps() {

            return new ArchiveDataSourceProperties();
        }


        @Bean
        @ConfigurationProperties("app.datasource.cluster.configuration")
        HikariDataSource clusterDataSource(ClusterDataSourceProperties props) {

            return props.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        }


        @Bean
        @ConfigurationProperties("app.datasource.archive.configuration")
        HikariDataSource archiveDataSource(ArchiveDataSourceProperties props) {

            return props.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        }
    }

    @ConfigurationProperties("app.datasource.cluster")
    public static class ClusterDataSourceProperties extends DataSourceProperties {

        // OK
    }

    @ConfigurationProperties("app.datasource.archive")
    public static class ArchiveDataSourceProperties extends DataSourceProperties {

        // OK
    }

    public static class ClusterJdbcTemplate extends JdbcTemplate {

        public ClusterJdbcTemplate(DataSource dataSource) {

            super(dataSource);
        }
    }

    public static class ArchiveJdbcTemplate extends JdbcTemplate {

        public ArchiveJdbcTemplate(DataSource dataSource) {

            super(dataSource);
        }
    }
}
