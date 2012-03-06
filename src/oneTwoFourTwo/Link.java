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
		g2.drawLine(bestPointOne.x, bestPointOne.y,
				bestPointTwo.x, bestPointTwo.y);
	}

}
