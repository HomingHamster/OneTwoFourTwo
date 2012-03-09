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
import javax.swing.JOptionPane;

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
			this.diagramPanel.generateCode();
		}
		
		else if (actionCommand.equals("Save")){
			SaveAndOpen.save(diagramPanel.controller, diagramPanel);
		}
		
		else if (actionCommand.equals("Open")){
			this.diagramPanel.loadIn(SaveAndOpen.open(this.diagramPanel));
		}
		
		else if (actionCommand.equals("Remove Class")){
			this.diagramPanel.removeClass();
		}
		
		else if (actionCommand.equals("New")){
			this.diagramPanel.controller = 
				new DragAndDropController(this.diagramPanel);
			this.diagramPanel.addMouseListener(this.diagramPanel.controller);
			this.diagramPanel.addMouseMotionListener(this.diagramPanel.controller);
			this.diagramPanel.repaint();
		}
		
		else if (actionCommand.equals("Help")){
			//The parent of this should probably be the frame itself, 
			//but i have a link to the diagramPanel here and available
			JOptionPane.showMessageDialog(diagramPanel,
					"This is SPARTA(help)!");
			//TODO write help..
		}
	}
}
