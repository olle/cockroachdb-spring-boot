
package com.studiomediatech.example;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StuffController {

	private static final BeanPropertyRowMapper<Stuff> MAPPER = BeanPropertyRowMapper.newInstance(Stuff.class);
	
	private final JdbcTemplate jdbcTemplate;

	public StuffController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@GetMapping
	public List<Stuff> stuff() {
		
		return jdbcTemplate.query("SELECT id, suffix, mime_type FROM stuff ORDER BY id", MAPPER);
	}

}