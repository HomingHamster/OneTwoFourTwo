package oneTwoFourTwo;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.event.MouseInputAdapter;

public class DragAndDropController extends MouseInputAdapter{
    Vector<DragAndDropClassObject> component = new Vector<DragAndDropClassObject>();
    Point offset = new Point();
    boolean dragging = false;
    DiagramPanel diagramPanel;
    
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
	
	public void addClass(String n, String desc){
		DragAndDropClassObject newComponent = new DragAndDropClassObject(n, desc);
        
		newComponent.addMouseListener(this);
        newComponent.addMouseMotionListener(this);
        
        component.add(newComponent);
	}
 
    public void mouseReleased(MouseEvent e) {
        dragging = false;
    }
 
    public void mouseDragged(MouseEvent e) {
        if(dragging) {
            int x = e.getX() - offset.x;
            int y = e.getY() - offset.y;
            //TODO: Protect from the outside of the panel boudaries.
            findNearestClass(x, y).setRectangleLocation(x, y);
        }
        diagramPanel.repaint();
    }
    
	public void drawAll(Graphics g) {
		DragAndDropClassObject currentClass;

		for(int i=0; i < component.size(); i++) {
			currentClass = (DragAndDropClassObject)(component.get(i));
			currentClass.paintComponent(g);
		}
	}
}