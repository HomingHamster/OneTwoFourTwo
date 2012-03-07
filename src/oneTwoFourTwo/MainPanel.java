/**
 * Based on classes Written by
 * @author Amy James (arj18)
 * Modified by Felix Farquharson (fef)
 * This class contains a pannel that contains the buttons and also
 * the diagram pannel.
 */


package oneTwoFourTwo;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

//The following because we are not going to be deserialzing 
//in another dimension any time soon.
@SuppressWarnings("serial")

public class MainPanel extends JPanel {
	JButton newClass, addLink;
	
	public MainPanel(DiagramPanel diagramPanel){
		MainPanelButtonListener buttonListener =
				new MainPanelButtonListener(diagramPanel);
		
		this.setLayout(new BorderLayout());
		
		/*
		 * Make a button, add to the left and watch for it being clicked.
		 */
		JButton newClassButton = new JButton("New Class");
		this.add(newClassButton, BorderLayout.LINE_START);
		newClassButton.addActionListener(buttonListener);
		
		/*
		 * Make a button, add on right.
		 */
		JButton addLinkButton = new JButton("Add Link"); 
		this.add(addLinkButton, BorderLayout.LINE_END);
		addLinkButton.addActionListener(buttonListener);
	
		/*
		 * Stick the diagram pannel in the middle of the two buttons.
		 */
		this.add(diagramPanel, BorderLayout.CENTER);
	}
}
