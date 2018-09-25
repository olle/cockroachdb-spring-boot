package com.studiomediatech.example.repo;

import com.studiomediatech.example.domain.Stuff;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Component;

import java.util.List;


public interface StuffRepository {

    List<Stuff> retriveAllStuff();
}

@Component
final class SimpleJdbcTemplateStuffRepositoryImpl implements StuffRepository {

    private static final BeanPropertyRowMapper<Stuff> MAPPER = BeanPropertyRowMapper.newInstance(Stuff.class);

    private final JdbcTemplate jdbcTemplate;

    public SimpleJdbcTemplateStuffRepositoryImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Stuff> retriveAllStuff() {

        return jdbcTemplate.query("SELECT id, suffix, mime_type FROM stuff ORDER BY id", MAPPER);
    }
}
