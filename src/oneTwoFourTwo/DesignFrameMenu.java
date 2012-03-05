package oneTwoFourTwo;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

//The following because we are not going to be deserialzing 
//in another dimension any time soon.
@SuppressWarnings("serial")

public class DesignFrameMenu extends JMenuBar {
	private JMenu fileMenu = new JMenu("File");
	private JMenu optionsMenu = new JMenu("Options");
	
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem open = new JMenuItem("Open");
	private JMenuItem quit = new JMenuItem("Quit");
	
	private JMenuItem newClass = new JMenuItem("New Class");
	private JMenuItem addLink = new JMenuItem("Add Link");
	private JMenuItem removeClass = new JMenuItem("Remove Class");
	
	public DesignFrameMenu() {
		this.add(fileMenu);
		this.add(optionsMenu);
		setVisible(true);
		
		DesignFrameMenuListener menuListener = new DesignFrameMenuListener();
		
        fileMenu.add(save);
        save.addActionListener(menuListener);
        
        fileMenu.add(open);
        open.addActionListener(menuListener);
        
        fileMenu.add(quit);
        quit.addActionListener(menuListener);
        
        optionsMenu.add(newClass);
        newClass.addActionListener(menuListener);
        
        optionsMenu.add(addLink);
        addLink.addActionListener(menuListener);
        
        optionsMenu.add(removeClass);
        removeClass.addActionListener(menuListener);
	}

}
