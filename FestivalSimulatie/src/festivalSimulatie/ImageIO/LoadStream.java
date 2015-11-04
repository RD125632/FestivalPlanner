package festivalSimulatie.ImageIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import Objecten.Festival;

public class LoadStream {
	
	private Festival festival;
	
	public Festival readFile(File file) throws ClassNotFoundException{
        try{
            ObjectInputStream in1 = new ObjectInputStream(new FileInputStream(file));
            if(in1.readInt() == 1){}
            this.festival = (Festival)in1.readObject();
            in1.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return festival;
    }
}
