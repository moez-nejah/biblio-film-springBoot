package com.biblio.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biblio.iservice.MovieDao;
import com.biblio.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/biblio")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class MovieController {


	
	@Autowired
    private MovieDao movieDao;
	
    @RequestMapping(value="/Movies", method=RequestMethod.GET)
    public List<Movie> listeMovies() throws IOException, ParseException {
        return movieDao.findAll()  ;
    }
    
    @RequestMapping(value="/Movie/show/{id}", method=RequestMethod.GET)
    public Movie getMovie(@PathVariable int id) throws IOException, ParseException {
        return movieDao.findById(id)  ;
    }
	
    @RequestMapping(value="/Movie/new", method=RequestMethod.POST)
    public Movie addMovie(@RequestBody HashMap<Object, Object> body)throws IOException, ParseException {
    	
		ObjectMapper mapper = new ObjectMapper();
			
			Movie movie = mapper.convertValue(body, Movie.class);
			return movieDao.save(movie)  ;
		
    }
	
    @RequestMapping(value="/Movie/update", method=RequestMethod.PUT)
    public Movie updateMovie(@RequestBody HashMap<Object, Object> body)throws IOException, ParseException {
    	
    	ObjectMapper mapper = new ObjectMapper();
	
			
			Movie movie = mapper.convertValue(body, Movie.class);
			return movieDao.update(movie)  ;	
		
    }
  	
  
  
    @RequestMapping(value="/Movie/delete/{id}", method=RequestMethod.DELETE)
    public Boolean removeMovie(@PathVariable int id)throws IOException, ParseException {
    
    	return	movieDao.remove(id) ;
    	
    }
    
    
    
}
