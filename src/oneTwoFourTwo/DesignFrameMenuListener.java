/**
 * Based on classes Written by
 * @author Amy James (arj18)
 * Modified by Felix Farquharson (fef)
 * This class defines the functionality of the buttons on the 
 * DesignFrameMenu.
 */

package oneTwoFourTwo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DesignFrameMenuListener implements ActionListener {
	private DiagramPanel diagramPanel;
	
	public DesignFrameMenuListener(DiagramPanel diagramPanel){
		this.diagramPanel = diagramPanel;
	}
	
	public void actionPerformed(ActionEvent actionEvent)
	{
		String actionCommand = actionEvent.getActionCommand();

		if(actionCommand.equals("Quit")){
			System.exit(0);
		}
		
		else if (actionCommand.equals("New Class")){
			this.diagramPanel.addClass();
		}
		
		else if (actionCommand.equals("Add Link")){
			this.diagramPanel.addLink();
		}
		
		else if (actionCommand.equals("Generate Java Code")){
			//TODO: generate
		}
		
		else if (actionCommand.equals("Save")){
			//TODO: save somehow
		}
		
		else if (actionCommand.equals("Load")){
			//TODO: load maybe
		}
		else if (actionCommand.equals("Remove Class")){
			//TODO: remove class
		}
	}
}
