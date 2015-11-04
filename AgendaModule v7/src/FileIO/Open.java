package FileIO;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import Objecten.Festival;

public class Open  {
	
	public Festival festival;

	//@Override
	public void openFile() throws ClassNotFoundException {
		
		// TODO Auto-generated method stub
		JFileChooser opener = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Festival Planner Data", "fpl");
        opener.setFileFilter(filter);
        
        //REPLACE null with the main JFrame!
        int returnVal = opener.showOpenDialog(SwingUtilities.getWindowAncestor(opener));
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	
        	if(opener.getSelectedFile() != null)
        	{
        		LoadStream loadstream = new LoadStream();
        		this.festival = loadstream.readFile(opener.getSelectedFile());
        	
        		//filename.setText(c.getSelectedFile().getName());
        		//dir.setText(c.getCurrentDirectory().toString());
        	}
        	else
        	{
        		festival = null;
        	}
        }
        if (returnVal == JFileChooser.CANCEL_OPTION) {
            
        }
	}
	
	public Festival getFestivalObject()
	{
		return festival;
	}
	

}
