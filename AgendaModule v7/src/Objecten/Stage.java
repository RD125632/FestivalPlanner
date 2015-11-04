package Objecten;
import java.util.ArrayList;


public class Stage extends Building{

	private static final long serialVersionUID = 3846707591702333836L;
	
	private ArrayList<Performance> lineUp;
	
	public Stage(String name)
	{	
		setName(name);
		this.lineUp = new ArrayList<Performance>();
	}
	
	public void addPerformance(Performance performance)
	{
		lineUp.add(performance);
	}
	
	public void setName(String name){ this.name = name; }
	public String getName(){ return name; }
	
	
	public ArrayList<Performance> getLineUp()
	{
		return lineUp;
	}


	@Override
	public String toString() {
		return "Stage [Name=" + name + ", "
				+ "lineUp=" + lineUp + "]";
	}
	
	

	
	
}
