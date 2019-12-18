package com.biblio.model;

import java.util.Date;

public class Movie {

	private long id ; 
    private String title;
    private String realisateur;
    private String releaseDate;
    private String type;
    
    

	
	public Movie() {
		super();
	}
	public Movie(long id, String title, String realisateur, String releaseDate, String type) {
		super();
		this.id = id;
		this.title = title;
		this.realisateur = realisateur;
		this.releaseDate = releaseDate;
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRealisateur() {
		return realisateur;
	}
	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", realisateur=" + realisateur + ", releaseDate=" + releaseDate
				+ ", type=" + type + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (id != other.id)
			return false;
		return true;
	}


	
    
    
}
