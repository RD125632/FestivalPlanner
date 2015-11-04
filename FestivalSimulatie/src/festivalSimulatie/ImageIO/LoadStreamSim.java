package festivalSimulatie.ImageIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import simulator.Tile;
import festivalSimulatie.Panels.Item;

public class LoadStreamSim {
	
	private ArrayList<Item> itemsForView;
	private ArrayList<Item> items;
	private List<Tile> tiles;
	
	@SuppressWarnings("unchecked")
	public void readFile(File file) throws ClassNotFoundException{
        try{
            ObjectInputStream in1 = new ObjectInputStream(new FileInputStream(file));
            if(in1.readInt() == 2){}
            {
            this.itemsForView = (ArrayList<Item>)in1.readObject();
            this.items = (ArrayList<Item>)in1.readObject();
            this.tiles = (List<Tile>)in1.readObject();

            }
            in1.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
    }

	public ArrayList<Item> getItemsForView() {
		return itemsForView;
	}

	public ArrayList<Item> getItems() {
		return items;
	}
	
	public List<Tile> getTiles()
	{
		return tiles;
	}
	
	
}
