package Objecten;
import java.io.Serializable;


public class Performance implements Serializable 
{
	private static final long serialVersionUID = 8937556675870607395L;
	
	private Time time;
	private Artist artist;
	private String id;
	

	
	public Performance(String begin, String end)
	{
		time = new Time(begin, end);
	}
	
	public void setArtist(Artist artist)
	{ 
		this.artist = artist;	
	}
	
	public void setTime(String begin, String end)
	{
		time.setBeginTime(begin); 	
		time.setEndTime(end); 
	}
	
	
	
	public Artist getArtist()
	{ 
		return artist; 
	}
	
	public Time getTime()
	{ 
		return time; 
	}
	
	public String getId()
	{ 
		return id; 
	}
	
	
	public String toString() 
	{
		return "Performance [Time=" + time + ", Artist=" + artist + "]";
	}
		
}
