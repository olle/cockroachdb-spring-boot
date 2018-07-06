package com.studiomediatech.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StuffController {

	private final JdbcTemplate jdbcTemplate;
	private final RowMapper<Stuff> rowMapper;

	public StuffController(JdbcTemplate jdbcTemplate, RowMapper<Stuff> rowMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.rowMapper = rowMapper;
	}
	
	@GetMapping
	public List<Stuff> stuff() {
		
		return jdbcTemplate.query("SELECT * FROM stuff ORDER BY id", this.rowMapper);
	}

}

@Component
class StuffRowMapper implements RowMapper<Stuff> {

	@Override
	public Stuff mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Stuff s = new Stuff();
		
		s.setId(rs.getString(1));
		s.setSuffix(rs.getString(2));
		s.setMimeType(rs.getString(3));
		
		return s;
	}
	
}