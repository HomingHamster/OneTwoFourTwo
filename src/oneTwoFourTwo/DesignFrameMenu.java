/**
 * Based on classes Written by
 * @author Amy James (arj18)
 * Modified by Felix Farquharson (fef)
 * This class defines the menu for the DesignFrame.
 */

package oneTwoFourTwo;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

//The following because we are not going to be deserialzing 
//in another dimension any time soon.
@SuppressWarnings("serial")

public class DesignFrameMenu extends JMenuBar {
	/*
	 * Define the layout of the menu.
	 */
	private JMenu fileMenu = new JMenu("File");
	private JMenu optionsMenu = new JMenu("Options");
	
	private JMenuItem newDiagram = new JMenuItem("New");
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem open = new JMenuItem("Open");
	private JMenuItem quit = new JMenuItem("Quit");
	
	private JMenuItem newClass = new JMenuItem("New Class");
	private JMenuItem addLink = new JMenuItem("Add Link");
	private JMenuItem removeClass = new JMenuItem("Remove Class");
	private JMenuItem generateJavaCode = new JMenuItem("Generate Java Code");
	private JMenuItem help = new JMenuItem("Help");
	
	public DesignFrameMenu(DiagramPanel diagramPanel) {
		this.add(fileMenu);
		this.add(optionsMenu);
		setVisible(true);
		
		/*
		 * Refence the custom listener for the menu buttons
		 */
		DesignFrameMenuListener menuListener = new DesignFrameMenuListener(diagramPanel);
		
		fileMenu.add(newDiagram);
		newDiagram.addActionListener(menuListener); //Adds listener to button.
		
        fileMenu.add(save);
        save.addActionListener(menuListener); //Adds listener to button.
        
        fileMenu.add(open);
        open.addActionListener(menuListener); //Adds listener to button.
        
        fileMenu.add(quit);
        quit.addActionListener(menuListener); //Adds listener to button.
        
        optionsMenu.add(newClass);
        newClass.addActionListener(menuListener); //Adds listener to button.
        
        optionsMenu.add(addLink);
        addLink.addActionListener(menuListener); //Adds listener to button.
        
        optionsMenu.add(removeClass);
        removeClass.addActionListener(menuListener); //Adds listener to button.
        
        optionsMenu.add(generateJavaCode);
        generateJavaCode.addActionListener(menuListener); //Adds listener to button.
        
        optionsMenu.add(help);
        help.addActionListener(menuListener); //Adds listener to button.
	}

}
