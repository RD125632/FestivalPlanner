package festivalSimulatie.Panels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import simulator.InputHandler;
import simulator.NPC;
import simulator.Tile;
import festivalSimulatie.ImageIO.ImageLoader;
import festivalSimulatie.Object.DigitalClock;

public class ViewPanel extends JPanel implements ActionListener
{

	private static final String PATH = "res/grass.jpg";
	public float cameraScale = 1;
	public Point2D cameraPoint = new Point2D.Double(0, 0);
	private static final long serialVersionUID = 1L;
	private List<Tile> tiles = new ArrayList<Tile>();
	private List<NPC> NPCs = new ArrayList<NPC>();
	private boolean delay = false;
	private BufferedImage image;
	private Timer timerSimulation,updateTimer;
	private DigitalClock clock;
	private int amountOfVisitors = 50;
	
	public void setTiles(List<Tile> tileLoad)
	{
		tiles = tileLoad;
	}
	
	public ViewPanel(JFrame frame)
	{
		setBounds(10, 10, 751, 500);
		image = ImageLoader.loadGround(PATH);
		new InputHandler(frame, this, tiles);
		new Timer(1000,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delay = !delay;
			}
		}).start();
		//initTest();
		
		timerSimulation = new Timer(1000/60,this);
		clock = new DigitalClock(timerSimulation);
		updateTimer = new Timer(1000/60, new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();				
			}
		});
		updateTimer.start();
	}
	
	private void initTest(){
		Tile tile1 = new Tile(new Point2D.Double(0, 0));
		Tile tile2 = new Tile(new Point2D.Double(200 + 100, 0));
		Tile tile3 = new Tile(new Point2D.Double(0, 200 + 100));
		Tile tile4 = new Tile(new Point2D.Double(200 + 100, 200 + 100));
		
		tile1.setBuilding(true);
		tile3.setExit(true);
		tile4.setEntrance(true);

		tile1.setName("Building");
		tile2.setName("Crossing");
		tile3.setName("Exit");
		tile4.setName("Entrance");
		
		tiles.add(tile1);
		tiles.add(tile2);
		tiles.add(tile3);
		tiles.add(tile4);
	
		tile4.addPath(tile1, tile2);
		tile2.addPath(tile1, tile1);
		
		checkVisitors();
	}
	
	private void checkVisitors() {
			for (int i = NPCs.size(); i < amountOfVisitors; i++) {
				NPC npc;
				Tile entrance;
				Point2D point;
				List<Tile> entrances = new ArrayList<Tile>();
				List<Tile> exits = new ArrayList<Tile>();
				for (Tile tile : tiles) {
					if (tile.isEntrance()) {
						entrances.add(tile);
					}
					if(tile.isExit()){
						exits.add(tile);
					}
				}
				
				if(entrances.isEmpty() || exits.isEmpty()){
					JOptionPane.showMessageDialog(this, "you need to have atleast 1 entrance and exit");
					clock.getTimer().stop();
					break;
				}
				entrance = entrances.get(new Random().nextInt(entrances.size()));
				int n = 0;
				do {
					n++;
					point = new Point2D.Double(
							entrance.getPosition().getX() + Math.random() * entrance.getWidth(), 
							entrance.getPosition().getY() + Math.random() * entrance.getHeight());

					npc = new NPC(point, tiles);
				} while (npc.hasCollision(NPCs) && n < 20);
				
				if (delay && n < 20) {
					NPCs.add(npc);
					break;
				}
			}
		} 

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setTransform(getCamera());
		
		TexturePaint p = new TexturePaint(image, new Rectangle2D.Double(0,	0, 100, 100));
		g2.setPaint(p);
		g2.fill(new Rectangle2D.Double(-2500, -2500, 5000,	5000));
		
		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).paint(g2);
		}
		for (int x = 0; x < NPCs.size(); x++) {
			NPCs.get(x).paint(g2);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		for(NPC npc : NPCs){
			npc.update(NPCs);
		}
		
		Iterator<NPC> it = NPCs.iterator();
		while(it.hasNext()){
			for(Tile tile : tiles){
				if(tile.isExit() && tile.getRect().contains(it.next().getPosition())){
					it.remove();
				}
			}
		}
		checkVisitors();		
		clock.tick();
	}
	
	public AffineTransform getCamera() {
		AffineTransform tx = new AffineTransform();
		tx.translate(-cameraPoint.getX() + getWidth() / 2, -cameraPoint.getY() + getHeight() / 2);
		tx.scale(cameraScale, cameraScale);
		return tx;
	}
	
	public DigitalClock getClock(){
		return clock;
	}

	public void setCameraPoint(Point2D point){
		this.cameraPoint = point;
	}

	public Point2D getCameraPoint() {
		return cameraPoint;
	}
	
	public float getCameraScale(){
		return cameraScale;
	}
	
	public void setCameraScale(float cameraScale){
		this.cameraScale = cameraScale;
	}
	
	public void setAmountOfVisitors(int amountOfVisitors){
		this.amountOfVisitors = amountOfVisitors;
	}
	
	public List<Tile> getTiles(){
		return tiles;
	}
}
