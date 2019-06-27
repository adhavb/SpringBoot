package com.bhuvan.springboot.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bhuvan.springboot.controller.CustomerController;
import com.bhuvan.springboot.model.Customers;
import com.bhuvan.springboot.model.Genre;
import com.bhuvan.springboot.model.GenreRowMapper;
import com.bhuvan.springboot.model.Movie;
import com.bhuvan.springboot.model.MovieRowMapper;

@Repository
public class MovieService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Genre> getAllGenres(){
		List<Genre> genreList = new ArrayList();
		String sql ="SELECT id,name FROM react_genre";
		genreList = jdbcTemplate.query(sql, new ResultSetExtractor<List<Genre>>() {
            public List<Genre> extractData(ResultSet resultSet) throws SQLException,
              DataAccessException {
            	List<Genre> localGenreList = new ArrayList();
                while (resultSet.next()) {
                	Genre localGenre = new Genre(resultSet.getInt(1),resultSet.getString(2));
                	localGenreList.add(localGenre);
                }
                return localGenreList;
            }
          }
      );		
		return genreList;
	}
	
	public Genre getGenre(int genreId){
		String query = "SELECT * FROM react_genre WHERE ID = ?";
		Genre genre = jdbcTemplate.queryForObject(
			    query, new Object[] { genreId }, new GenreRowMapper());
		return genre;
	}
	
	public Movie getMovie(int movieId){
		String query = "SELECT a.*,b.* FROM react_movies a, react_genre b WHERE a.ID = ? and a.genreid = b.id";
		Movie movie = jdbcTemplate.queryForObject(
			    query, new Object[] { movieId }, new MovieRowMapper());
		return movie;
	}
	
	public List<Movie> getAllMovies(){
		List<Movie> movieList = new ArrayList();
		String sql ="SELECT * FROM react_movies";
		movieList = jdbcTemplate.query(sql, new ResultSetExtractor<List<Movie>>() {
            public List<Movie> extractData(ResultSet resultSet) throws SQLException,
              DataAccessException {
            	List<Movie> localMovieList = new ArrayList();
                while (resultSet.next()) {
                	
                	Movie localMovie = new Movie(resultSet.getInt(1),resultSet.getString(2),getGenre(resultSet.getInt(3)), resultSet.getInt(4),resultSet.getDouble(5));
                	localMovieList.add(localMovie);
                }
                return localMovieList;
            }
          }
      );		
		return movieList;
	}
	
	public boolean saveMovie(Movie movie){
		String sql ="insert into react_movies (title,genreid,stock,rent) values(?,?,?,?)";			
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		    	@Override
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(sql, new String[] {"id"});
		            ps.setString(1, movie.getTitle());		            
		            ps.setInt(2, movie.getGenreId());
		            ps.setInt(3, movie.getNumberInStock());		            
		            ps.setDouble(4, movie.getDailyRentalRate());
		            return ps;
		        }
		    },
		    keyHolder);
		int generatedKey = keyHolder.getKey().intValue();
		System.out.println(generatedKey);
		if(generatedKey > 0) return true;
		return false;		
		
	}
	
	public boolean updateMovie(int id, Movie movie){
		String sql ="update react_movies set title = ?, genreid=?, stock = ?, rent = ? where id = ?";
		int count = jdbcTemplate.update(sql, movie.getTitle(),movie.getGenreId(),movie.getNumberInStock(),movie.getDailyRentalRate() ,id);
		if(count > 0)
			return true;
		else
			return false;
	}
	
	public boolean deleteMovie(int movieId){
		String sql ="DELETE FROM react_movies where id = ?";
		int count = jdbcTemplate.update(sql, movieId);
		if(count > 0)
			return true;
		else
			return false;
	}
}
