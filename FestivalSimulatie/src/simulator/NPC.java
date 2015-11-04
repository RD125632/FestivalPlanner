package simulator;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;


public class NPC{
	private static final String PATH = "res/visitor.png";
	private Image image;
	private Point2D position;
	private double speed,direction;
	private Tile target,destination;
	private List<Tile> tiles;
	
	public NPC(Point2D position, List<Tile> tiles){
		image = new ImageIcon(PATH).getImage();
		this.position = position;
		this.tiles = tiles;
		speed = 1;
		direction = 1;
		getDestination();
		newTarget();
	}
	
	public void getDestination() {
		Random random = new Random();
		while (true) {
			int number = random.nextInt(tiles.size());
			if (tiles.get(number).isBuilding() || tiles.get(number).isExit()) {
				destination = tiles.get(number);
				break;
			}
		}
	}
	
	public void update(List<NPC> andereNPCs) {
		rotations();

		Point2D oldPositie = position;
		position = new Point2D.Double(position.getX() + speed * Math.cos(direction), 
									  position.getY() + speed * Math.sin(direction));

		if (hasCollision(andereNPCs)) {
			position = oldPositie;
			direction += 0.2;
		}
		newTarget();
	}
	
	public void rotations() {
		direction += 0.01;

		Point2D difference = new Point2D.Double(target.getCenter().getX() - position.getX(),
				target.getCenter().getY() - position.getY());

		double newRotation = Math.atan2(difference.getY(), difference.getX());
		double rotDifference = direction - newRotation;

		while (rotDifference > Math.PI) {
			rotDifference -= 2 * Math.PI;
		}
		
		while (rotDifference < -Math.PI) {
			rotDifference += 2 * Math.PI;
		}
		
		if (Math.abs(rotDifference) < 0.1) {
			direction = newRotation;
		} else if (rotDifference < 0) {
			direction += 0.1;
		} else if (rotDifference > 0) {
			direction -= 0.1;
		}
	}
		
	public boolean hasCollision(List<NPC> NPCs) {
		for (NPC npc : NPCs) {
			if (npc != this){				
				if (npc.position.distance(position) < 15){
					return true;
				}
			}
		}
		return false;
	}
	
	public void newTarget() {
		if (!(tiles.isEmpty())) {
			if (!(target == null)) {
				if (target.getRect().contains(position)) {
					if (destination.getRect().contains(position)) {
						Timer timer = new Timer((int) (30000 * Math.random()),
								new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										getDestination();
									}
								});
						timer.setRepeats(false);
						timer.start();
					}
					for (int i = 0; i < target.getPaths().size(); i++) {
						if (target.getPaths().get(i).getDestination().equals(destination)) {
							target = target.getPaths().get(i).getTarget();
							break;
						}
					}
				}
			}else{
				for(int i = 0 ; i < tiles.size() ; i++){
					if (tiles.get(i).isEntrance()) {
						target = tiles.get(i);
						break;
					}
				}
			}
		} else {
			target = destination;
		}
	}
	
	public NPC returnSelf() {
		return this;
	}

	public void paint(Graphics2D g)
	{
		AffineTransform tx = new AffineTransform();
		tx.translate(position.getX()-15/2, position.getY()-15/2);
		tx.rotate(direction, 8, 8);
		
		g.drawImage(image, tx ,null);
	}
	
	public Point2D getPosition()
	{
		return position;
	}
}
