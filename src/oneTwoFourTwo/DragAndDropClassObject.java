/**
 * Based on classes Written by
 * @author Amy James (arj18)
 * Modified by Felix Farquharson (fef)
 * Defines all information about a class including all information 
 * needed to draw the rectangles at a visual level.
 * It contains a reference to the ClassObject where the non-design 
 * aspects are stored.
 */

package oneTwoFourTwo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JPanel;


public class DragAndDropClassObject extends JPanel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3644934051095422457L;
	Vector<Link> links = new Vector<Link>();
	ClassObject classObject;
	Rectangle rect;
	String name;
	
	/*
	 * Create the class a rectangle, but it won't be drawn
	 * until paintComponent is called and that where the
	 * magic happens.
	 * Also creates the class object and stores it.
	 */
	public DragAndDropClassObject(String n, String desc){
		rect = new Rectangle(10,10,0,75); //has no width until drawn.
		name = n;
		ClassObject classObject = new ClassObject(n, desc);
		this.classObject = classObject;
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * Does all of the clever drawing magic working out the width based
	 * on text width using code from http://stackoverflow.com/questions/
	 * 258486/calculate-the-display-width-of-a-string-in-java.
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		rect.height = 75; //reset every draw, otherwise it will continually grow on repaint.
		int stringWidth = g.getFontMetrics().stringWidth(name);
		int lineHeight = g.getFontMetrics().getHeight()+5;
		//For every attribute, add a new line's worth of space virtically in rect
		//plus assess to see if the text is wider, and if it is then save the new
		//textwidth into stringWidth to make the box wider.
		for (int i = 0; i < classObject.attributes.size(); i++){
			rect.height += lineHeight;
			if (stringWidth<g.getFontMetrics().stringWidth(classObject.attributes.get(i))){
				stringWidth = g.getFontMetrics().stringWidth(classObject.attributes.get(i));
			}
		}
		//Does the same as the above code, but for the methods.
		for (int i = 0; i < classObject.methods.size(); i++){
			rect.height += lineHeight;
			if (stringWidth<g.getFontMetrics().stringWidth(classObject.methods.get(i))){
				stringWidth = g.getFontMetrics().stringWidth(classObject.methods.get(i));
			}
		}
		
		//make the rectangle the width of the largest string plus 20px.
		rect.width = stringWidth+20;
		
		//Ask the renderer to use antialiasing if possible.
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
		
		//set paint to black + draw outline of rect
		g2.setPaint(Color.BLACK);
		g2.draw(rect);
		
		//set paint to white + fill rectangle
		g2.setPaint(Color.WHITE);
		g2.fill(rect);
		
		//set paint to black + draw title and the line accross the top
		//use the rectangle as a point of reference on the diagram panel.
		g2.setPaint(Color.BLACK);
		g2.drawString(name, rect.x+10, rect.y+20);
		g2.drawLine(rect.x, rect.y+30, rect.x+stringWidth+20, rect.y+30);
		
		//for attributes draw the string, work out the placement based
		//on how many attributes there are every time.
		for(int i = 0; i < classObject.attributes.size(); i++){
			String attrib = classObject.attributes.get(i);
			g2.drawString(attrib, rect.x+10, rect.y+35+((lineHeight+2)*(i+1)));
		}
		
		// Draw the line below the attributes above the methods.
		// work out spacing based on how many attributes there are
		g2.drawLine(rect.x, rect.y+40+
				((lineHeight+2)*(classObject.attributes.size())), rect.x+stringWidth+20, rect.y+40+
				((lineHeight+2)*(classObject.attributes.size())));
		
		//draw mathods, take into account the spacing of the lines and the
		//amount of room the attributes take up.
		for(int i = 0; i < classObject.methods.size(); i++){
			String method = classObject.methods.get(i);
			g2.drawString(method, rect.x+10, rect.y+45+
					((lineHeight+2)*(classObject.attributes.size()+1))+
					(lineHeight+2)*(i));
		}
	}
	
	/*
	 * When object is dragged this is called to move the class on the
	 * diagram pannel.
	 */
	public void setRectangleLocation(int x, int y){
		rect.setLocation(x,y);
	}
	
	/*
	 * when the mouse is clicked this is used to find the nearest 
	 * rectangle so that somthing can be changed with it.
	 */
	public int distanceTo(int x, int y)
	{
		return (Math.abs(this.rect.x-x) + Math.abs(this.rect.y-y));
	}
}