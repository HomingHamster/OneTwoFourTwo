/**
 * Based on classes Written by
 * @author Amy James (arj18)
 * Modified by Felix Farquharson (fef)
 * This class defines the Diagram Pannel, which is where 
 * the diagram information will be drawn/inputted.
 */

package oneTwoFourTwo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//The following because we are not going to be deserialzing 
//in another dimension any time soon.
@SuppressWarnings("serial")

public class DiagramPanel extends JPanel{
	/*
	 * Create an instance of the d and d controller to take 
	 * care of the classes inputted.
	 */
	public DragAndDropController controller = 
		new DragAndDropController(this);
	
	/*
	 * Sets backround to grey and puts the d and d controller
	 * in charge of watching for any mouse activity.
	 */
	public DiagramPanel() {
		this.setBackground(Color.GRAY);
		this.addMouseListener(controller);
		this.addMouseMotionListener(controller);
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * Extends the functionality of the paintConponent function to make
	 * sure that all individual objects get drawn. It puts the controller
	 * in charge as this is where the list of objects is stored.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.controller.drawAll(g);
		this.controller.drawLinks(g);
	}
	
	/*
	 * Gather necessarry information for class creation then tell the 
	 * controller to add the necessarry class, before makeing sure that 
	 * the pane is redrawn to show the new class.
	 */
	public void addClass() {
		String name = JOptionPane.showInputDialog("Enter Class Name:");
		//TODO: check name doesn't exist.
		String description = JOptionPane.showInputDialog("Enter Class description:");
		controller.addClass(name, description);
		this.repaint();
	}
	
	/*
	 * Request name and pass name to the controller for clean removal.
	 * TODO: not working..
	 */
	public void removeClass() {
		String name = JOptionPane.showInputDialog("Enter Class Name:");
		controller.removeClass(name);
	}
	
	/*
	 * Adds a link without knowing where it is coming from first, 
	 * all we need to know is the name then the controller can take over.
	 */	
	public void addLink(){
		String name = JOptionPane.showInputDialog("Enter Class One Name:");
		controller.addLink(name);
		this.repaint();
	}
	
	/*
	 * Asks for relevant info about editing a link with no prior knowledge
	 * then ask controller to take over.
	 */
	public void editLink(){
		String classOneName = JOptionPane.showInputDialog("Enter Class One Name:");
		String classTwoName = JOptionPane.showInputDialog("Enter Class Two Name:");
		controller.editLink(classOneName, classTwoName);
		this.repaint();
	}
	
	/*
	 * Shortcut to the d and d controller's find nearest class function
	 * the work is done in the other class because that is where the 
	 * information is stored.
	 */
	public DragAndDropClassObject findNearestClass(int x, int y){
		return controller.findNearestClass(x, y);
	}
	
	/*
	 * This function will take the code generated and will output it to 
	 * bunch of files in a location that the user chooses.
	 */
	public void generateCode(){
		JFileChooser fileopen = new JFileChooser();
		String fileLocation;
		//We only want a directory, not a file, because we already know 
		//the filename.
		fileopen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    int returnVal = fileopen.showOpenDialog(this);
	    //If it worked then pass the directory location to the generate
	    //code function in the controller.
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	            fileLocation = fileopen.getSelectedFile().getPath();
	            controller.generateCode(fileLocation);
	    } else { //If it didn't work:
	    	//THROW A TANTRUM!!!
	    }
		
	}
	
	/*
	 * Accept a new drag and drop controller and save it where it should be
	 * then repaint.
	 */
	public void loadIn(DragAndDropController controller){
		//load in controller
		this.controller = controller;
		//reset listeners
		this.addMouseListener(controller);
		this.addMouseMotionListener(controller);
		//redraw
		this.repaint();
	}
	
	public void showHelp(){
		File openAs = new File("README.txt");
		FileReader in;
		JFrame helpFrame = new JFrame();
		helpFrame.setSize(600,420);
		helpFrame.setLocation(120,120);
		JTextArea helpText = new JTextArea();
		helpText.setFont(new Font("Serif", Font.PLAIN, 12));
		helpText.setLineWrap(true);
		helpText.setWrapStyleWord(true);
		helpText.setEditable(false);
		try {
			in = new FileReader(openAs);
			helpText.read(in, openAs.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JScrollPane jsp = new JScrollPane(helpText);
		helpFrame.add(jsp);
		helpFrame.setVisible(true);
	}

}
