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
		
		if(actionCommand.equals("New Class")) {
			//if new class button selected then pass to
			//diagram panel to complete
			this.diagramPanel.addClass();
		} else if(actionCommand.equals("Add Link")){
			//if new link is requested pass to diagram panel
			//not controller because we don't know where the
			//link is coming from.
			this.diagramPanel.addLink();
		} else {
			System.out.println("Unexpected Command");
		}
	}
}
