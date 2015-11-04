package festivalSimulatie.Object;

public class Gate extends Building {
	private static final long serialVersionUID = 1L;

	public Gate(String name, String pathToFile, int populariteit,int capaciteit) 
	{
		super(name, pathToFile, populariteit, capaciteit, true);
	}
	
	public Gate(){
		super(null,null,0,0,true);
	};
}
