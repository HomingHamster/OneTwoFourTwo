package oneTwoFourTwo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;

//The following because we are not going to be deserialzing 
//in another dimension any time soon.
@SuppressWarnings("serial")

public class DragAndDropClassObject extends JPanel {
	ClassObject classObject;
	Rectangle rect;
	String name;
	
	public DragAndDropClassObject(String n, String desc){
		rect = new Rectangle(10,10,0,75);
		name = n;
		ClassObject classObject = new ClassObject(n, desc);
		this.classObject = classObject;
	}
	
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
	
	public void setRectangleLocation(int x, int y){
		rect.setLocation(x,y);
	}
	
	public int distanceTo(int x, int y)
	{
		return (Math.abs(this.rect.x-x) + Math.abs(this.rect.y-y));
	}
}