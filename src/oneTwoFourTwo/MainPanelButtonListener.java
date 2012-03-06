/**
 * Based on classes Written by
 * @author Amy James (arj18)
 * Modified by Felix Farquharson (fef)
 * This class listens for any button activity on the main panel.
 */


package oneTwoFourTwo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanelButtonListener implements ActionListener {
	private DiagramPanel diagramPanel;
	
	public MainPanelButtonListener(DiagramPanel diagramPanel){
		this.diagramPanel = diagramPanel;
	}
	
	public void actionPerformed(ActionEvent actionEvent){
		String actionCommand = actionEvent.getActionCommand();
		
		if(actionCommand.equals("New Class"))
		{
			this.diagramPanel.addClass();
		}
		else
		{
			System.out.println("Unexpected Command");
		}
	}
}
