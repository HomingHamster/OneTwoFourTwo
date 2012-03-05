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
		JMenuBar menuBar = new DesignFrameMenu();
		setJMenuBar(menuBar);
		
		add(mainPanel);
		
		setSize(800,600);
		setLocation(100,100);
		setTitle("CS124 Group Project - BlueJish");
		setVisible(true);	
	}
}
