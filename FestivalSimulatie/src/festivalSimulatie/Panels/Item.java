package festivalSimulatie.Panels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.Serializable;

import simulator.Tile;
import festivalSimulatie.ImageIO.ImageLoader;
import festivalSimulatie.Object.Building;

public class Item extends Tile implements Serializable {

	private static final long serialVersionUID = 1L;
	private Rectangle2D.Float rectangle;
	private Building building;
	private Image image;
	private boolean isUsed;
	private String path;

	public double rotation, scale;
	public Point2D position, oldPosition;

	public Item(Rectangle2D.Float rect, String pathToFile, Building building) {
		super(new Point2D.Double(200,200));
		super.setName(building.getName());
		super.setBuilding(true);
		this.rectangle = rect;
		this.building = building;
		this.image = ImageLoader.loadImage(pathToFile);
		super.setHeight(image.getHeight(null));
		super.setWidth(image.getWidth(null));
		this.isUsed = false;
		this.path = pathToFile;
		this.position = new Point2D.Double(rectangle.getX(), rectangle.getY());
		this.rotation = 0;
		this.scale = 1;
	}
	public void paint(Graphics2D g)
	{
		super.setRect(new Rectangle2D.Double(super.getPosition().getX(), super.getPosition().getY(),super.getWidth(), super.getHeight()));
		g.setColor(Color.GRAY);
		g.draw(new Rectangle2D.Double(super.getRect().getBounds2D().getX() - 1, super.getRect().getBounds2D().getY() - 1,	super.getWidth() + 1, super.getHeight()+ 1));
		g.setColor(Color.LIGHT_GRAY);
		g.fill(super.getRect());
		g.setColor(Color.RED);
		g.drawString(super.getName(), (int) super.getPosition().getX(), (int) (super.getPosition().getY() + super.getHeight()));
		g.drawImage(image, (int)super.getRect().getBounds2D().getX(),  (int)super.getRect().getBounds2D().getY() - 100,  image.getWidth(null), image.getHeight(null), null);
	}
	public Rectangle2D.Float getRekt() {
		return rectangle;
	}

	public String getPath() {
		return path;
	}

	public Building getBuilding() {
		return building;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public AffineTransform getTransform() {
		AffineTransform tx = new AffineTransform();
		tx.translate(position.getX(), position.getY());
		tx.rotate(Math.toRadians(rotation), image.getWidth(null) / 2,
				image.getHeight(null) / 2);
		return tx;
	}

	public void setUsed(boolean used) {
		isUsed = used;
	}

	public void setOldPosition() {
		position = oldPosition;
	}

	public boolean contains(Point2D point) {
		Shape shape = new Rectangle2D.Double(0, 0, image.getWidth(null),
				image.getHeight(null));
		return getTransform().createTransformedShape(shape).contains(point);
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeObject(rectangle);
		out.writeObject(building);
		out.writeObject(position);
		out.writeObject(oldPosition);

		out.writeUTF(path);

		out.writeBoolean(isUsed);

		out.writeDouble(rotation);
		out.writeDouble(scale);

	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		rectangle = (Rectangle2D.Float) in.readObject();
		building = (Building) in.readObject();
		position = (Point2D) in.readObject();
		oldPosition = (Point2D) in.readObject();

		path = in.readUTF();

		isUsed = in.readBoolean();

		rotation = in.readDouble();
		scale = in.readDouble();

		image = ImageLoader.loadImage(path);
	}

}
