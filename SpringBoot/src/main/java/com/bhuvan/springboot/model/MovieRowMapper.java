package com.bhuvan.springboot.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MovieRowMapper implements RowMapper<Movie>{
	@Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
		Movie movie = new Movie(); 
		Genre genre = new Genre();
		movie.set_id(rs.getInt(1));
		movie.setTitle(rs.getString(2));
		genre.setId(rs.getInt(6));
		genre.setName(rs.getString(7));
		movie.setGenre(genre);
		movie.setNumberInStock(rs.getInt(4));
		movie.setDailyRentalRate(rs.getDouble(5));
        return movie;
    }

}
