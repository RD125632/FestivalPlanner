package simulator;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import festivalSimulatie.Panels.Item;
import festivalSimulatie.Panels.OptionsPanel;
import festivalSimulatie.Panels.ViewPanel;


public class InputHandler {
	private Tile tile;
	private Point2D clickPoint;
	private Point2D lastPoint;
	private Point2D lastMousePosition;
	private ViewPanel panel;
	public InputHandler(JFrame frame, ViewPanel panel, List<Tile> tiles){
		this.panel = panel;
		panel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				clickPoint = getClickPoint(e.getPoint());
				lastPoint = clickPoint;
				lastMousePosition = e.getPoint();
				for(Tile selectTile : tiles){
					if(contact(clickPoint,selectTile)){
						if(e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)){
							new PopUp(frame, selectTile, tiles);
							break;
						}
						else{
							tile = selectTile;
						}
					}
				}
				if(SwingUtilities.isMiddleMouseButton(e)){
					Tile tile = new Tile(clickPoint);
					tiles.add(tile);
					new PopUp(frame, tile, tiles);
				}
				if(tile != null){
				if(((Item) (tile)).getBuilding() != null){
					OptionsPanel.setPanel(((Item) (tile)).getBuilding());
				}}
			}
			public void mouseReleased(MouseEvent e) {
				tile = null;
			}
		});
		
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				clickPoint = getClickPoint(e.getPoint());
				if (tile != null) {
					if (SwingUtilities.isLeftMouseButton(e)) {
						tile.setPosition(new Point2D.Double(
								tile.getPosition().getX() - (lastPoint.getX() - clickPoint.getX()),
								tile.getPosition().getY() - (lastPoint.getY() - clickPoint.getY())));
					}else if (SwingUtilities.isRightMouseButton(e)){
						if(tile.getWidth() > 20)
							tile.setWidth(tile.getWidth() - (lastPoint.getX() - clickPoint.getX()));
						else
							tile.setWidth(21);
						if(tile.getHeight() > 20)
							tile.setHeight(tile.getHeight()- (lastPoint.getY() - clickPoint.getY()));
						else
							tile.setHeight(21);
					}
				} else{
						panel.setCameraPoint(new Point2D.Double(
								panel.getCameraPoint().getX() + (lastMousePosition.getX() - e.getX()),
								panel.getCameraPoint().getY() + (lastMousePosition.getY() - e.getY())));
				}
				
				lastMousePosition = e.getPoint();
				lastPoint = clickPoint;
				panel.repaint();
			}
		});
	
		panel.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				panel.setCameraScale((float) (panel.getCameraScale() * 1 - (e.getPreciseWheelRotation() / 10.0)));
				panel.repaint();
			}
		});
	}
	
	private boolean contact(Point2D clickPoint2, Tile tile) {
		return tile.getRect().contains(clickPoint2);
	}
	
	private Point2D getClickPoint(Point point) {
		try {
			return panel.getCamera().inverseTransform(point, null);
		} catch (NoninvertibleTransformException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}