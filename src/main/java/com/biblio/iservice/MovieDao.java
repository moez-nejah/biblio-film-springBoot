package com.biblio.iservice;

import java.util.ArrayList;
import java.util.List;

import com.biblio.model.Movie;

public interface MovieDao {


public List<Movie> findAll();
public Movie findById(long id);
public Movie findByTitle(String title);
public Movie save(Movie movie);
public Movie update(Movie movie);
public Boolean remove(long id);

}
