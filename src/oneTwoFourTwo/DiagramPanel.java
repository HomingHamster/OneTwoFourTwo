/**
 * Based on classes Written by
 * @author Amy James (arj18)
 * Modified by Felix Farquharson (fef)
 * This class defines the Diagram Pannel, which is where 
 * the diagram information will be drawn/inputted.
 */

package oneTwoFourTwo;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

//The following because we are not going to be deserialzing 
//in another dimension any time soon.
@SuppressWarnings("serial")

public class DiagramPanel extends JPanel{
	/*
	 * Create an instance of the d and d controller to take 
	 * care of the classes inputted.
	 */
	private DragAndDropController controller = 
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
	}
	
	/*
	 * Gather necessarry information for class creation then tell the 
	 * controller to add the necessarry class, before makeing sure that 
	 * the pane is redrawn to show the new class.
	 */
	public void addClass() {
		String name = JOptionPane.showInputDialog("Enter Class Name:");
		String description = JOptionPane.showInputDialog("Enter Class description:");
		controller.addClass(name, description);
		this.repaint();
	}
	
	/*
	 * TODO: Make and descibe this function.
	 */
	public void removeClass() {
		
	}
	
	/*
	 * Shortcut to the d and d controller's find nearest class function
	 * the work is done in the other class because that is where the 
	 * information is stored.
	 */
	public DragAndDropClassObject findNearestClass(int x, int y){
		return controller.findNearestClass(x, y);
	}

}
