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
    Vector<DragAndDropClassObject> components = new Vector<DragAndDropClassObject>();
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
    	for(int i=0; i<components.size() ; i++){
        	Rectangle r = components.get(i).rect;
	        if(r.contains(p)) {
	        	//blank icon because i can't figure out how to not have one
	        	ImageIcon icon = new ImageIcon("");
	        	//set options for the dialog
	        	Object[] possibilities = {"rename", "delete", "add link"};
	        	//present dialog and save answer to s
				String s = (String)JOptionPane.showInputDialog(diagramPanel, 
	        			"What would you like to do to the class?", 
	        			"Modify class..", 
	        			JOptionPane.PLAIN_MESSAGE, 
	        			icon, 
	        			possibilities, 
	        			"delete");
				//Asses the value returned and call necessarry function
	        	if (s=="delete"){
	        		removeClass(components.get(i).name);
	        		return;
	        	} else if (s=="rename"){
	        		renameClass(components.get(i).name);
	        		return;
	        	} else if (s=="add link"){
	        		return;
	        	} else {
	        		System.out.println("uh oh!");
	        		return;
	        	}
	        }
        }
    }
 
    /*
     * When the mouse is clicked, figure out if it is clicked inside a
     * class rectangle, so that somthing can be done to the class.
     */
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        
        for(int i=0; i<components.size() ; i++){
        	Rectangle r = components.get(i).rect;
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

		for(int i=0; i < components.size(); i++) {
			c = (DragAndDropClassObject)(components.get(i));
			if(c.distanceTo(x,y) < minDist) {
				minDist = c.distanceTo(x,y);
				minDistIndex = i;
			}
		}
		if((minDistIndex >= 0) && (minDist < 100)) {
			return components.get(minDistIndex);
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
        
        components.add(newComponent);
	}
	
	/*
	 * run through classes, if any found with the name specified, then
	 * remove the class from components.
	 */
	public void removeClass(String name){
		DragAndDropClassObject obj;
		for (int i=0;i<components.size();i++){
			obj = components.get(i);
			if (obj.name == name){
				components.remove(i);
				diagramPanel.repaint();
				return;
			}
		}
	}
	
	public void renameClass(String oldName){
		for (int i=0;i<components.size();i++){
			if (components.get(i).name == oldName){
				components.get(i).name = 
					JOptionPane.showInputDialog("Enter Class Name:");;
				diagramPanel.repaint();
				return;
			}
		}
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

		for(int i=0; i < components.size(); i++) {
			currentClass = (DragAndDropClassObject)(components.get(i));
			currentClass.paintComponent(g);
		}
	}
}