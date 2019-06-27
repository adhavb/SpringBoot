package com.bhuvan.springboot.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Movie {
	
	@Id
	@GeneratedValue
  	@Column(name="ID")
	private int _id;	
	
	private String title;
	
	private int genreId;
	
	private int numberInStock;
	
	private double dailyRentalRate;	
	
	private Genre genre;	
	
	public Movie(){
		
	}
	public Movie(int id, String title, Genre genre, int stock, double rent){
		this._id = id;
		this.title = title;
		this.genre = genre;
		this.numberInStock = stock;
		this.dailyRentalRate = rent;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int id) {
		_id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public int getNumberInStock() {
		return numberInStock;
	}
	public void setNumberInStock(int numberInStock) {
		this.numberInStock = numberInStock;
	}
	public double getDailyRentalRate() {
		return dailyRentalRate;
	}
	public void setDailyRentalRate(double dailyRentalRate) {
		this.dailyRentalRate = dailyRentalRate;
	}
	
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}
