package simulator;

import java.io.Serializable;

public class Path implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tile destination,target;
	
	public Path(Tile destination,Tile target){
		this.destination = destination;
		this.target = target;
	}
	
	public Tile getDestination(){
		return destination;
	}

	public Tile getTarget() {
		return target;
	}

	public void setTarget(Tile target) {
		this.target = target;
	}

	public void setDestination(Tile destination) {
		this.destination = destination;
	}
}
