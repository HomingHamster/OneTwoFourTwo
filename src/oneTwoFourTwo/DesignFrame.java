/**
 * Based on classes Written by
 * @author Amy James (arj18)
 * Modified by Felix Farquharson (fef)
 * This class is the main swing frame of the application, it
 * sets the size, and title etc.
 */

package oneTwoFourTwo;
import javax.swing.*;
import oneTwoFourTwo.DesignFrameMenu;

// The following because we are not going to be deserialzing 
// in another dimension any time soon.
@SuppressWarnings("serial")

public class DesignFrame extends JFrame {
	private DiagramPanel diagramPanel = new DiagramPanel();
	private MainPanel mainPanel = new MainPanel(diagramPanel);
	
	public DesignFrame(){
		/*
		 * The following assigns a menubar defines in 
		 * DesignFrameMenu to the frame.
		 */
		JMenuBar menuBar = new DesignFrameMenu(diagramPanel);
		setJMenuBar(menuBar);
		
		/*
		 * This adds the panel containing the buttons and 
		 * the design frame to the application.
		 */
		add(mainPanel);
		
		/*
		 * Sets size and location on screen.
		 */
		setSize(800,600);
		setLocation(100,100);
		
		setTitle("CS124 Group Project - BlueJish");
		
		setVisible(true);	
	}
}
