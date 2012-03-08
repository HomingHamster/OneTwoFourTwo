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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	        	Object[] possibilities = {"rename", "delete", "add link", "add attribute", "add method"};
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
	        		addLink(components.get(i).name);
	        		return;
	        	} else if (s=="add attribute"){
	        		DragAndDropClassObject component = components.get(i);
	        		String attribute = 
						JOptionPane.showInputDialog("Enter attrubute details:\neg. \"-timeTaken:String\"");
	        		component.classObject.attributes.add(attribute);
	        		diagramPanel.repaint();
	        	} else if (s=="add method"){
	        		DragAndDropClassObject component = components.get(i);
	        		String method = 
						JOptionPane.showInputDialog("Enter method details:\neg. \"-getBanana(String time):String\"");
	        		component.classObject.methods.add(method);
	        		diagramPanel.repaint();
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
	
	public void addLink(String classOneName){
		for (int i=0;i<components.size();i++){
			if (components.get(i).name.equals(classOneName)){
				String classTwoName = 
					JOptionPane.showInputDialog("Enter Class Two Name:");
				String oneToManyQuantifier = 
					JOptionPane.showInputDialog("Enter a cardinality:");
				components.get(i).links.add(new Link(classTwoName, 
						oneToManyQuantifier));
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
	
	/*
	 * Some funky jazz to draw the links, 
	 * TODO: draw the arrow head.
	 * TODO: draw the quantifier.
     * TODO: change from one line into two squared parts.
	 */
	public void drawLinks(Graphics g){
		Vector<Link> currentLinks;
		String obj;

		for(int i=0; i < components.size(); i++) {
			currentLinks = (Vector<Link>)(components.get(i).links);
			for(int j=0; j < currentLinks.size(); j++) {
				Link currentLink = currentLinks.get(j);

				for(int m=0; m < components.size(); m++) {
					obj = components.get(m).name;
					if (obj.equals(currentLink.classTwoName)){
						currentLink.paintComponent(g, components.get(i), components.get(m));
						break;
					}
				}
			}
		}
	}
	
	/*
	 * Feed correct info to the generator for code generation.
	 */
	public void generateCode(String fileLocation){
		//Make a list of Strings and populate it with the generated code
		//from the generator.
		Vector<String> output = CodeGenerator.generateCodeForVectorOfClasses(components);
		//for each string in the list (which is a file)
		for (String file:output){
			//find out the name of the file from the comment on the
			//top line of the string.
			String topline = file.split("\\n")[0];
			String file_name = topline.split("/")[2];
			//reference a file with the selected path and name:
			File writeFile = new File(fileLocation + "\\" + file_name);
			//check if it exists already
			boolean exist = true;
			try {
				exist = writeFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!exist) {
				  System.out.println("File already exists.");
			} else {
				//If it doesn't exist, then proceed with writing to it.
				//try catch in case it doesn't work here.
				FileWriter fstream = null;
				try {
					fstream = new FileWriter(fileLocation + "\\" + file_name);
					BufferedWriter out = new BufferedWriter(fstream);
					out.write(file); //output the file
					out.close();
				} catch (IOException e) {
					//catch any errors here.
					e.printStackTrace();
				}
			}
		}
	}
}