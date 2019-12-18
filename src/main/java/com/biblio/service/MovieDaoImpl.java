package com.biblio.service;

import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.biblio.iservice.MovieDao;
import com.biblio.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.json.simple.parser.JSONParser;

@Service
public class MovieDaoImpl implements MovieDao {

	@Autowired
	public MovieDao movieDao;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Movie> findAll() {

		ArrayList<Movie> movies1 = new ArrayList<Movie>();
		try {

			// Décaration d'un objet de type JSON
			JSONParser parser = new JSONParser();

			// récupération de la liste des films à partir de notre fichier JSON
			JSONArray data = (JSONArray) parser
					.parse(new FileReader("C:\\Users\\Moez\\Documents\\movies\\movies.json"));// path to the JSON file.

			Gson gson = new GsonBuilder().create();
			if (data.size() != 0) {
				for (int i = 0; i <= data.size() - 1; i++) {
					if (data.get(i) != null) {
						Movie movie11 = (Movie) gson.fromJson(data.get(i).toString(), Movie.class);
						movies1.add(movie11);
					}
				}
				return movies1;
			}

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Movie save(Movie movie) {
		
		List<Movie> listMovies ;
			if(movieDao.findAll()==null)
			{
				listMovies= new ArrayList<Movie>() ;
			}
			else {
			 listMovies = movieDao.findAll();
			}
			
		int max = 0;
		if (listMovies.size() != 0) {
			max = (int) listMovies.get(0).getId();
			
			for (int i = 0; i <= listMovies.size() - 1; i++) {
				if ((int) listMovies.get(i).getId() > max) {
					max = (int) listMovies.get(i).getId();
				}
			}
		}

		String date_sortie = movie.getReleaseDate().substring(0, 10);
		String annee = date_sortie.substring(0, 4);
		String mois = date_sortie.substring(5, 7);
		String jour = date_sortie.substring(8, 10);
		date_sortie = jour + "/" + mois + "/" + annee;

		movie.setReleaseDate(date_sortie);
		movie.setId(max + 1);
		
		listMovies.add(movie);
		//logger.info(listMovies.toString());

		String json = new Gson().toJson(listMovies);

		try (FileWriter file = new FileWriter("C:\\Users\\Moez\\Documents\\movies\\movies.json")) {
			file.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return movie;

	}

	@Override
	public Movie update(Movie movie) {

		List<Movie> listMovies = movieDao.findAll();
		Boolean test = false;

		if (listMovies.size() != 0) {
			for (int i = 0; i <= listMovies.size() - 1; i++) {
				if (listMovies.get(i).getId() == movie.getId()) {
					test = true;
					listMovies.remove(listMovies.get(i));

					String date_sortie = movie.getReleaseDate();

					
				/*	String annee = date_sortie.substring(0, 4);
					String mois = date_sortie.substring(5, 7);
					String jour = date_sortie.substring(8, 10); 
					
					

					date_sortie = jour + "/" + mois + "/" + annee; 
					
					System.out.println(date_sortie);
					movie.setReleaseDate(date_sortie);
			*/

					listMovies.add(movie);
				}
			}

		}

		if (test == true) {
			String json = new Gson().toJson(listMovies);
			try (FileWriter file = new FileWriter("C:\\Users\\Moez\\Documents\\movies\\movies.json")) {
				file.write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return movie;
		}

		return null;

	}

	@Override
	public Movie findById(long id) {
		Movie movie = null;

		Gson gson = new GsonBuilder().create();
		List<Movie> movies = movieDao.findAll();
		if (movies.size() != 0) {
			for (int i = 0; i <= movies.size() - 1; i++) {
				if (movies.get(i).getId() == id) {
					return movies.get(i);
				}
			}
		}
		return null;
	}

	@Override
	public Movie findByTitle(String title) {

		Movie movie = null;
		List<Movie> movies = movieDao.findAll();

		for (int i = 0; i <= movies.size() - 1; i++) {
			if (movies.get(i).getTitle().equals(title)) {

				movie = movies.get(i);
				return movie;
			}
		}
		return null;
	}

	@Override
	public Boolean remove(long id) {

		List<Movie> movies = movieDao.findAll();
		Movie movie = movieDao.findById(id);

		if (movies != null) {
			Boolean t = (movies.remove(movie));
			if (t == true) {
				String json = new Gson().toJson(movies);
				try (FileWriter file = new FileWriter("C:\\Users\\Moez\\Documents\\movies\\movies.json")) {
					file.write(json);
				} catch (IOException e) {
					e.printStackTrace();
				}

				return true;
			}
		}

		return false;
	}

}
