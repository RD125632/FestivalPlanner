package FileIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Objecten.Festival;


/**
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SaveStream
{
    

	public void saveToFile(File file,Festival festival) {
        
		try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file)); 
           
            output.writeInt(1);      
            output.writeObject(festival);
            output.close();
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    
    
}
