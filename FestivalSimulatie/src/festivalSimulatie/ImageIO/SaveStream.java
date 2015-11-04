package festivalSimulatie.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import simulator.Tile;
import festivalSimulatie.Panels.Item;

public class SaveStream
{
	public void saveToFile(File file,ArrayList<Item> itemsForView,ArrayList<Item> items, List<Tile> tile) {
		try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeInt(2);      
            output.writeObject(itemsForView);
            output.writeObject(items);
            output.writeObject(tile);
            output.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    } 
}
