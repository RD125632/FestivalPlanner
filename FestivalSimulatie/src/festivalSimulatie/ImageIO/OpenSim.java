package festivalSimulatie.ImageIO;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import simulator.Tile;
import festivalSimulatie.Panels.Item;

public class OpenSim  {

	public ArrayList<Item> itemsForView;
	public ArrayList<Item> items;
	public List<Tile> tiles;

	public void openFile() throws ClassNotFoundException {
		JFileChooser opener = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Festival Simulator Data", "fsd");
		opener.setFileFilter(filter);

		int returnVal = opener.showOpenDialog(SwingUtilities.getWindowAncestor(opener));
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			if(opener.getSelectedFile() != null)
			{
				LoadStreamSim loadstream = new LoadStreamSim();
				loadstream.readFile(opener.getSelectedFile());
				this.itemsForView = loadstream.getItemsForView();
				this.items = loadstream.getItems();
				this.tiles = loadstream.getTiles();
			}else{
				items = null;
			}
		}else if(returnVal == JFileChooser.CANCEL_OPTION){
				items = null;
		}
	}

	public ArrayList<Item> getItemsForViewObject(){
		return itemsForView;
	}
	public ArrayList<Item> getItemsObject(){
		return items;
	}
	
	public List<Tile> getTilesObject()
	{
		return tiles;
	}


}
