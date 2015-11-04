package festivalSimulatie.ImageIO;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import Objecten.Festival;

public class OpenFPL {

	public Festival festival;

	public void openFile() throws ClassNotFoundException {
		JFileChooser opener = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Festival Planner Data", "fpl");
		opener.setFileFilter(filter);

		int returnVal = opener.showOpenDialog(SwingUtilities
				.getWindowAncestor(opener));
		if (returnVal == JFileChooser.APPROVE_OPTION) 
		{
			if (opener.getSelectedFile() != null) {
				LoadStream loadstream = new LoadStream();
				this.festival = loadstream.readFile(opener.getSelectedFile());
			} else {
				festival = null;
			}
		}
		else if (returnVal == JFileChooser.CANCEL_OPTION) 
		{
			System.exit(0);
		}

		
	}

	public Festival getFestivalObject() {
		return festival;
	}

}
