package Objecten;

import java.io.Serializable;
import java.util.ArrayList;


public class Festival implements Serializable{
	
	private static final long serialVersionUID = 8693151764036872847L;
	
	private String name;
	private ArrayList<Stage> stages;
	private ArrayList<Artist> artists;
	
	public Festival(String name)
	{
		setName(name);
		this.artists = new ArrayList<Artist>();
		this.stages = new ArrayList<Stage>();
	}
	
	//Set-Get Festival name
	public void setName(String name){ this.name = name; }
	public String getName(){ return name; }
	
	//Add to arraylist
	public void addStage(Stage stage){ 		stages.add(stage); 		}
	public void addArtist(Artist artist){ 	artists.add(artist); 	}
	
	//Get ArrayList
	public ArrayList<Stage> getStage(){ 	return stages; 	}
	public ArrayList<Artist> getArtists(){ 	return artists; }

	@Override
	public String toString()
	{
		return "Festival [name=" + name + ", "
				+ "stages=" + stages + ", "
				+ "artists=" + artists +"]";
	}
	
	
	
	
}
