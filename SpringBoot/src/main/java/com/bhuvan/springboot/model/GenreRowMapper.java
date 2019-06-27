package com.bhuvan.springboot.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GenreRowMapper implements RowMapper<Genre>{
	@Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
		Genre genre = new Genre(); 
		genre.setId(rs.getInt(1));
		genre.setName(rs.getString(2));
        return genre;
    }
}
