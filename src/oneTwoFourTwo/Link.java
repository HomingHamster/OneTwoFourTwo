/**
 * Based on classes Written by
 * @author Amy James (arj18)
 * Modified by Felix Farquharson (fef)
 * This class defines a simple link, it will be held by one class,
 * so it only references the second class.
 */
package oneTwoFourTwo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.Vector;

public class Link {
	String classTwoName;
	String quantifier;
	
	public Link(String name, String quant){
		this.classTwoName = name;
		this.quantifier = quant;
	}
	
	/*
	 * This method contains all the logic necessarry to figure out
	 * the best path for the line to take and draws the line on the fly.
	 */
	public void paintComponent(Graphics g, 
			DragAndDropClassObject classOne, 
			DragAndDropClassObject classTwo){
		Vector<Point> pointsOne = new Vector<Point>();
		Vector<Point> pointsTwo = new Vector<Point>();
		Vector<DragAndDropClassObject> ddClasses = 
			new Vector<DragAndDropClassObject>();
		ddClasses.add(classOne);
		ddClasses.add(classTwo);
		
		Vector<Point> currentPoints;
		DragAndDropClassObject currentClass;
		
		//Loops through both of the classes on the diagram
		//and finds all the points available from both as Points (x,y)
		//on the diagram pannel, it sticks these variables into
		//pointsOne and pointsTwo as necessarry.
		for (int i=0; i<ddClasses.size();i++){
			currentPoints = new Vector<Point>();
		    currentClass = ddClasses.get(i);
		
			currentPoints.add(new Point(currentClass.rect.x, currentClass.rect.y));
			currentPoints.add(new Point(currentClass.rect.x + 
					(currentClass.rect.width/2), currentClass.rect.y));
			currentPoints.add(new Point(currentClass.rect.x +
					(currentClass.rect.width), currentClass.rect.y));
			currentPoints.add(new Point(currentClass.rect.x + 
					(currentClass.rect.width), currentClass.rect.y +
					(currentClass.rect.height/2)));
			currentPoints.add(new Point(currentClass.rect.x + 
					(currentClass.rect.width), currentClass.rect.y +
					(currentClass.rect.height)));
			currentPoints.add(new Point(currentClass.rect.x + 
					(currentClass.rect.width/2), currentClass.rect.y +
					(currentClass.rect.height)));
			currentPoints.add(new Point(currentClass.rect.x, currentClass.rect.y+
					(currentClass.rect.height)));
			currentPoints.add(new Point(currentClass.rect.x, currentClass.rect.y+
					(currentClass.rect.height/2)));
			
			if(i==0){
				pointsOne = currentPoints;
			} else if (i==1){
				pointsTwo = currentPoints;
			}
			
		}
		
		/*
		 * next we loop through all of the possible points combinations
		 * and note the shortest one available and where both ends are.
		 */
		int x;
		int y;
		Point bestPointOne = new Point(0,0);
		Point bestPointTwo = new Point(0,0);
		int distance = 9999;
		
		for (Point pointOne:pointsOne){
			for (Point pointTwo:pointsTwo){
				x = Math.abs(pointOne.x-pointTwo.x);
				y = Math.abs(pointOne.y-pointTwo.y);
				if (x+y<distance){
					distance=x+y;
					bestPointOne = pointOne;
					bestPointTwo = pointTwo;
				}
			}
		}
		
		/*
		 * finally we draw the graphics to the diagram pannel
		 * TODO: make the lines squared and more like blueJ.
		 */
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(Color.BLACK);
		g2.draw(new Line2D.Double(new Point(bestPointOne.x, bestPointOne.y),
				new Point(bestPointTwo.x, bestPointTwo.y)));
		drawArrowHead(g2, bestPointTwo, bestPointOne, Color.black);
		drawQuantifier(g2, bestPointTwo, bestPointOne, this.quantifier);
		}
	
	/*
	 * Code for drawing the arrow head was taken from the website
	 * http://www.coderanch.com/t/340443/GUI/java/Draw-arrow-head-end-line.
	 */
	private void drawArrowHead(Graphics2D g2, Point tip, Point tail, Color color) {  
		double phi;  
	    int barb;
        phi = Math.toRadians(40);  
        barb = 20;
        g2.setPaint(color);  
        double dy = tip.y - tail.y;  
        double dx = tip.x - tail.x;  
        double theta = Math.atan2(dy, dx);
        double x, y, rho = theta + phi;  
        for(int j = 0; j < 2; j++)  
        {  
            x = tip.x - barb * Math.cos(rho);  
            y = tip.y - barb * Math.sin(rho);  
            g2.draw(new Line2D.Double(tip.x, tip.y, x, y));  
            rho = theta - phi;  
        }  
    }
	
	/*
	 * code below positions and draws the cardinality for the link.
	 * It is based on the same code that draws the arrow hear and it
	 * is far from perfect.
	 */
	private void drawQuantifier(Graphics2D g2, Point tip, Point tail, String quant){
		double phi;  
	    int barb;
        phi = Math.toRadians(40);  
        barb = 35; 
        double dy = tip.y - tail.y;  
        double dx = tip.x - tail.x;  
        double theta = Math.atan2(dy, dx); 
        double x, y, rho = theta + phi;
        x = tip.x - barb * Math.cos(rho);  
        y = tip.y - barb * Math.sin(rho); 
        g2.drawString(quant, (int)x, (int)y);
	}

}
