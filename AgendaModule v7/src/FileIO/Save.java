package FileIO;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import Objecten.Festival;

public class Save 
{
	
	//@Override
	public void saveFile(Festival festival) 
	{
		
		// TODO Auto-generated method stub
		JFileChooser saver= new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Festival Planner Data", "fpl");
        saver.setFileFilter(filter);
        
        
        int returnVal = saver.showSaveDialog(SwingUtilities.getWindowAncestor(saver));
        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            
            SaveStream savestream = new SaveStream();
            File file = new File(saver.getSelectedFile()+".fpl");
            
            savestream.saveToFile(file,festival);
            
            //filename.setText(c.getSelectedFile().getName());
            //dir.setText(c.getCurrentDirectory().toString());
        }
        if (returnVal == JFileChooser.CANCEL_OPTION) 
        {

        }
		
		
	}
	
	
	
	
	

}
