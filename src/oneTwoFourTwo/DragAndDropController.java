/**
 * Based on classes Written by
 * @author Amy James (arj18)
 * Modified by Felix Farquharson (fef)
 * This class acts as the mouse listener and also stores a list of all
 * the classes on the diagram pannel.
 */

package oneTwoFourTwo;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputAdapter;

public class DragAndDropController extends MouseInputAdapter{
    Vector<DragAndDropClassObject> component = new Vector<DragAndDropClassObject>();
    Point offset = new Point();
    boolean dragging = false;
    DiagramPanel diagramPanel;
    
    /*
     * on creation takes a note of the diagram pannel so it can be used
     * later.
     */
    public DragAndDropController(DiagramPanel dp){
    	this.diagramPanel = dp;
    }
 
//    public void addDragAndDropController(DragAndDropClassObject gdad, DiagramPanel diagramPanel) {
//        DragAndDropClassObject newComponent = gdad;
//        newComponent.addMouseListener(this);
//        newComponent.addMouseMotionListener(this);
//        component.add(newComponent);
//        this.diagramPanel = diagramPanel;
//    }
    
    /*
     * If mouse is clicked then show an option dialog to ask what
     * the user was *hoping* to do with that class.
     */
    public void mouseClicked(MouseEvent e){
    	Point p = e.getPoint();
    	for(int i=0; i<component.size() ; i++){
        	Rectangle r = component.get(i).rect;
	        if(r.contains(p)) {
	        	//blank icon because i can't figure out how to not have one
	        	ImageIcon icon = new ImageIcon("");
	        	//set options for the dialog
	        	Object[] possibilities = {"rename", "delete", "add link"};
	        	//present dialog and save answer to s
	        	@SuppressWarnings("unused")
				String s = (String)JOptionPane.showInputDialog(diagramPanel, 
	        			"What would you like to do to the class?", 
	        			"Modify class..", 
	        			JOptionPane.PLAIN_MESSAGE, 
	        			icon, 
	        			possibilities, 
	        			"delete");
	        	//TODO: use s!
	        }
        }
    }
 
    /*
     * When the mouse is clicked, figure out if it is clicked inside a
     * class rectangle, so that somthing can be done to the class.
     */
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        
        for(int i=0; i<component.size() ; i++){
        	Rectangle r = component.get(i).rect;
	        if(r.contains(p)) {
	            offset.x = p.x - r.x;
	            offset.y = p.y - r.y;
	            dragging = true;
	        }
        }
    }
    
    /*
     * Logic for finding the nearest class, from one of lynda's examples
     * i think.
     */
	public DragAndDropClassObject findNearestClass(int x, int y){
		DragAndDropClassObject c;
		double minDist = Double.MAX_VALUE;
		int minDistIndex = -1;

		for(int i=0; i < component.size(); i++) {
			c = (DragAndDropClassObject)(component.get(i));
			if(c.distanceTo(x,y) < minDist) {
				minDist = c.distanceTo(x,y);
				minDistIndex = i;
			}
		}
		if((minDistIndex >= 0) && (minDist < 100)) {
			return component.get(minDistIndex);
		}
	    return null;
	}
	
	/*
	 * add, store and watch any new classes passed from diagram pannel.
	 */
	public void addClass(String n, String desc){
		DragAndDropClassObject newComponent = new DragAndDropClassObject(n, desc);
        
		newComponent.addMouseListener(this);
        newComponent.addMouseMotionListener(this);
        
        component.add(newComponent);
	}
 
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
    public void mouseReleased(MouseEvent e) {
        dragging = false;
    }
 
    /*
     * (non-Javadoc)
     * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
     * As you would expect, this is allows the rectangles to be dragged and adjusts 
     * the location of the rectangles as they move. Finally calling repaint to make
     * sure that the diagram pannel notices the change.
     */
    public void mouseDragged(MouseEvent e) {
        if(dragging) {
            int x = e.getX() - offset.x;
            int y = e.getY() - offset.y;
            //TODO: Protect from the outside of the panel boudaries.
            findNearestClass(x, y).setRectangleLocation(x, y);
        }
        diagramPanel.repaint();
    }
    
    /*
     * run through each class drawing it to the diagram pannel with 
     * its information.
     */
	public void drawAll(Graphics g) {
		DragAndDropClassObject currentClass;

		for(int i=0; i < component.size(); i++) {
			currentClass = (DragAndDropClassObject)(component.get(i));
			currentClass.paintComponent(g);
		}
	}
}