package com.bhuvan.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bhuvan.springboot.model.FoodCategory;
import com.bhuvan.springboot.model.FoodProduct;
import com.bhuvan.springboot.model.Genre;
import com.bhuvan.springboot.model.Movie;
import com.bhuvan.springboot.model.ResponseStatus;
import com.bhuvan.springboot.service.MovieService;


@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	@GetMapping("/genre/getall")
	public ResponseEntity<Genre> getAllGenres(){
		return new ResponseEntity(movieService.getAllGenres(),HttpStatus.OK);
	}
	
	@GetMapping("/movies/getall")
	public ResponseEntity<Movie> getAllMovies(){
		return new ResponseEntity(movieService.getAllMovies(),HttpStatus.OK);
	}
	
	@GetMapping("/movies/getmovie/{movieId}")
	public ResponseEntity<Movie> getMovie(@PathVariable int movieId){		
		return new ResponseEntity(movieService.getMovie(movieId),HttpStatus.OK);
	}
	
	@PostMapping("/movies/save")
	public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){		
		return new ResponseEntity(movieService.saveMovie(movie),HttpStatus.OK);
	}
	
	@PutMapping("/movies/update/{movieId}")
	public ResponseEntity<Movie> updateMovie(@PathVariable int movieId, @RequestBody Movie movie){
		System.out.println(movieId);
		return new ResponseEntity(movieService.updateMovie(movieId, movie),HttpStatus.OK);
	}
	
	@DeleteMapping("/movies/delete/{movieId}")
	public ResponseEntity<Movie> deleteMovie(@PathVariable int movieId){
		System.out.println(movieId);
		return new ResponseEntity(movieService.deleteMovie(movieId),HttpStatus.OK);
	}
	
	

}
