package simulator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name = "Tile";
	private Point2D position;
	private Shape rect;
	private boolean isBuilding;
	private boolean isEntrance;
	private boolean isExit;
	private double width  = 100,
				   height = 100;
	private List<Path> Paths = new ArrayList<Path>();

	public Tile(Point2D position){
		this.position = position;
		isBuilding = false;
		isEntrance = false;
		isExit = false;
		rect = new Rectangle2D.Double(position.getX(), position.getY(), width, height);
	}

	public Shape getRect(){
		return rect;
	}
	
	public void setRect(Shape s){
		this.rect =s;
	}
	
	public void setPosition(Point2D position){
		this.position = position;
	}
	
	public Point2D getPosition(){
		return position;
	}

	public Point2D getCenter(){
		Point2D center = new Point2D.Double(position.getX()+width/2,position.getY()+height/2);
		return center;
	}

	public void addPath(Tile destination, Tile target){
		Paths.add(new Path(destination,target));
	}
	
	public List<Path> getPaths() {
		return Paths;
	}
	
	public void setBuilding(boolean isBuilding){
		this.isBuilding = isBuilding;
	}
	
	public boolean isBuilding(){
		return isBuilding;
	}
	
	public boolean isEntrance() {
		return isEntrance;
	}

	public void setEntrance(boolean isEntrance) {
		this.isEntrance = isEntrance;
	}
	
	public void paint(Graphics2D g)
	{
		rect = new Rectangle2D.Double(position.getX(), position.getY(),	width, height);
		g.setColor(Color.GRAY);
		g.draw(new Rectangle2D.Double(rect.getBounds2D().getX() - 1, rect.getBounds2D().getY() - 1,	width + 1, height + 1));
		g.setColor(Color.LIGHT_GRAY);
		g.fill(rect);
		g.setColor(Color.RED);
		g.drawString(name, (int) position.getX(), (int) (position.getY() + height));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setExit(boolean isExit){
		this.isExit = isExit;
	}
	public boolean isExit() {
		return isExit;
	}
	
	public void setWidth(double width){
		this.width = width;
	}
	
	public void setHeight(double height){
		this.height = height;
	}
	
	public double getWidth(){
		return width;
	}
	
	public double getHeight(){
		return height;
	}
	
	public String toString(){
		return name;
	}
}
