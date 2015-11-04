package festivalSimulatie.ImageIO;

import java.util.List;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import simulator.Tile;
import festivalSimulatie.Panels.Item;


public class Save 
{
	public void saveFile(ArrayList<Item> itemsForView, ArrayList<Item> items, List<Tile> tiles) 
	{
		JFileChooser saver = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Festival Simulator Data", "fsd");
		saver.setFileFilter(filter);

		int returnVal = saver.showSaveDialog(SwingUtilities
				.getWindowAncestor(saver));
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			SaveStream savestream = new SaveStream();

			File file = new File(saver.getSelectedFile()+".fsd");
			savestream.saveToFile(file, itemsForView, items, tiles);
		}
	}

}
