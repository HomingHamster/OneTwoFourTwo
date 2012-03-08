package oneTwoFourTwo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

public class SaveAndOpen {
	public static DragAndDropController open(DesignFrame designFrame){
		JFileChooser fileSelect = new JFileChooser();
		fileSelect.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    int returnVal = fileSelect.showOpenDialog(designFrame);
	    //If it worked then pass the directory location to the generate
	    //code function in the controller.
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	            String fileLocation = fileSelect.getSelectedFile().getPath();
	            DragAndDropController ddDetails = null;
	    		FileInputStream fis = null;
	    		ObjectInputStream in = null;
	    		try {
	    			fis = new FileInputStream(fileLocation);
	    			in = new ObjectInputStream(fis);
	    			ddDetails = (DragAndDropController)in.readObject();
	    			in.close();
	    			return ddDetails;
	    		} catch (IOException ex) {
	    			ex.printStackTrace();
	    		} catch (ClassNotFoundException ex) {
	    			ex.printStackTrace();
	    		}

	    } else { //If it didn't work:
	    	//THROW A TANTRUM!!!
	    }
		return null;
	}
	
	public static void save(DragAndDropController controller, DiagramPanel diagramPanel){
		JFileChooser fileSelect = new JFileChooser();
		fileSelect.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    int returnVal = fileSelect.showOpenDialog(diagramPanel);
	    //If it worked then pass the directory location to the generate
	    //code function in the controller.
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	            String fileLocation = fileSelect.getSelectedFile().getPath();
	   	     try
		      {
		         FileOutputStream fileOut =
		         new FileOutputStream(fileLocation);
		         ObjectOutputStream out =
		                            new ObjectOutputStream(fileOut);
		         out.writeObject(controller);
		         out.close();
		          fileOut.close();
		      }catch(IOException i)
		      {
		          i.printStackTrace();
		      }

	    } else { //If it didn't work:
	    	//THROW A TANTRUM!!!
	    }
	}

}
