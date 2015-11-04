package festivalSimulatie.Object;

import Objecten.Stage;

public class StageS extends Building {
	private static final long serialVersionUID = 1L;
	private String beginTime;
	private String endTime;
	private Stage stage;

	public StageS(String name, String pathToFile, int populariteit,int capaciteit,String beginTime, String endTime,Stage stage,boolean isStackable) {
		super(name, pathToFile, populariteit, capaciteit, isStackable);
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.stage=stage;
	}
	
	public StageS(){
		super(null,null,0,0,false);
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String toString() {		
		return "stage";
	}

	public Stage getAgendaStage()
	{
		return stage;
	}	
}
