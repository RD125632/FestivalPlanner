package festivalSimulatie.Object;

import java.io.Serializable;

public class Building implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name, path;
	private int scale = 1;
	private int rotation = 0;
	int populariteit;
	private int capaciteit;
	private boolean stackable;

	public Building(String name, String pathToFile, int populariteit, int capaciteit,boolean stackable) {
		this.name = name;
		this.path = pathToFile;
		this.populariteit = populariteit;
		this.capaciteit = capaciteit;
		this.stackable=stackable;		
	}	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public int getPopulariteit() {
		return populariteit;
	}

	public void setPopulariteit(int populariteit) {
		this.populariteit = populariteit;
	}

	public int getCapaciteit() {
		return capaciteit;
	}

	public void setCapaciteit(int capaciteit) {
		this.capaciteit = capaciteit;
	}

	public boolean isStackable()
	{
		return stackable;
	}

	public String toString() {	
		return "building";
	}
}
