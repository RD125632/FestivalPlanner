package Objecten;

import java.io.Serializable;

public class Artist implements Serializable {


	private static final long serialVersionUID = -5829022650471358132L;
	
	private String name;
	private int popularity;
	private String genre;

	public Artist(String name, int popularity, String genre)
	{
		this.setName(name);
		this.setPopularity(popularity);
		this.setGenre(genre);
	}
	
	//Setters
	public void setName(String name){ 			this.name = name; 				}
	public void setPopularity(int popularity){ 	this.popularity = popularity; 	}
	public void setGenre(String genre){ 		this.genre = genre; 			}

	
	//Getters
	public String getName(){ 	return this.name; 		}
	public int getPopularity(){ return this.popularity; }
	public String getGenre(){ 	return this.genre; 		}
	
	
	//To-String
	@Override
	public String toString() 
	{
		return "Artist [Name=" + name + ", "
				+ "Popularity=" + popularity
				+ ", Genre=" + genre + "]";
	}
	
		
}
