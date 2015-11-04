package festivalSimulatie.Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import Objecten.Festival;
import Objecten.Stage;
import festivalSimulatie.ImageIO.ImageLoader;
import festivalSimulatie.Object.StageS;

public class BuildingPanel extends JPanel  {
	private static final long serialVersionUID = 1L;
	private ArrayList<Item> items  = new ArrayList<>();
	private static ArrayList<Item> itemsForView;
	private int columns,row;
	private Festival festival;
	private ViewPanel viewPanel;

	public BuildingPanel(Festival festival,ViewPanel viewPanel)
	{
		setBounds(771, 10, 240, 500);
		this.festival = festival;
		this.viewPanel = viewPanel;
		BuildingPanel.itemsForView = new ArrayList<>();
		
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				tick(e);
			}
		});

		init();
		
	}

	public void tick(MouseEvent e){
		for(Item item : items){
			if(item.getRekt().contains(e.getPoint())){
				OptionsPanel.setPanel(item.getBuilding());
				if(!item.isUsed()){
					viewPanel.getTiles().add(item);
					//itemsForView.add(item);
					if(!item.getBuilding().isStackable()){
						item.setUsed(true);
					}
				}
			}
		}
		repaint();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.LIGHT_GRAY);
		g2.fill(new Rectangle2D.Double(0, 0, this.getWidth(), getHeight()));

		Color color = new Color(255, 0, 0, 128);

		int row = 0;
		int columns = 0;
		for (Item item : items) {
			BufferedImage image = ImageLoader.loadImage(item.getPath());

			g2.setColor(Color.LIGHT_GRAY);
			g2.fill(new Rectangle2D.Double(20 + 106 * columns, 25 + 106 * row, 96, 96));
			g2.drawImage(image, 20 + 106 * columns, 25 + 106 * row,	96, 96, null);
			
			if (item.isUsed() == true) {
				g2.setColor(color);
				g2.fill(new Rectangle2D.Double( 20 + 106 * columns,	25 + 106 * row, 96, 96));
			}
			
			columns++;
			if (columns == 2) {
				columns = 0;
				row++;
			}
		}
	}

	public void reInit(ArrayList<Item> itemsForView, ArrayList<Item> items) {
		BuildingPanel.itemsForView = itemsForView;
		this.items = items;
		repaint();
	}

	public void reInit(Festival festival) {
		this.festival = festival;
		init();
	}

	public static ArrayList<Item> getViewableItems() {
		return itemsForView;
	}
	
	public void init()
	{
		items = new ArrayList<Item>();
		row = 0;
		columns= 0;
		for(Stage stage : festival.getStage()){
			if(stage.getLineUp().isEmpty() == true)
			{
				items.add( new Item( new Rectangle2D.Float(20+106*columns,25 + 106*row,96,96),
									 "/stage31.png",
									 new StageS(stage.getName(),"/stage31.png", 0, 0,"", "",stage,false)));
				
				columns++;
			}
			else
			{
				items.add(new Item(new Rectangle2D.Float(20+106*columns,25 + 106*row, 96, 96),
								   "/stage31.png", 
								   new StageS(stage.getName(),"/stage31.png", 0, 0,stage.getLineUp().get(0).getTime().getBeginTime(), stage.getLineUp().get(0).getTime().getEndTime(),stage,false)));
				
				columns++;
			}
			
			if(columns==2){
				columns=0;
				row++;
			}
		}
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
}
