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
import java.util.Vector;

import javax.swing.JPanel;

//The following because we are not going to be deserialzing 
//in another dimension any time soon.
@SuppressWarnings("serial")

public class DragAndDropClassObject extends JPanel {
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
		int stringWidth = g.getFontMetrics().stringWidth(name);
		rect.width = stringWidth+20;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(Color.BLACK);
		g2.draw(rect);
		g2.setPaint(Color.WHITE);
		g2.fill(rect);
		g2.setPaint(Color.BLACK);
		g2.drawString(name, rect.x+10, rect.y+20);
		g2.drawLine(rect.x, rect.y+30, rect.x+stringWidth+20, rect.y+30);
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