package oneTwoFourTwo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DesignFrameMenuListener implements ActionListener {
	
	public DesignFrameMenuListener(){
	}
	
	public void actionPerformed(ActionEvent actionEvent)
	{
		String actionCommand = actionEvent.getActionCommand();

		if(actionCommand.equals("Quit")){
			System.exit(0);
		}
		
		else if (actionCommand.equals("New Class")){
			//TODO: add class
		}
		
		else if (actionCommand.equals("Add Link")){
			//TODO: add link
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
