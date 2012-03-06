package oneTwoFourTwo;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

//The following because we are not going to be deserialzing 
//in another dimension any time soon.
@SuppressWarnings("serial")

public class DiagramPanel extends JPanel{
	private DragAndDropController controller = new DragAndDropController(this);
	
	public DiagramPanel() {
		this.setBackground(Color.GRAY);
		this.addMouseListener(controller);
		this.addMouseMotionListener(controller);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.controller.drawAll(g);
	}
	
	public void addClass() {
		String name = JOptionPane.showInputDialog("Enter Class Name:");
		String description = JOptionPane.showInputDialog("Enter Class description:");
		controller.addClass(name, description);
		this.repaint();
	}
	
	public void removeClass() {
		
	}
	
	public DragAndDropClassObject findNearestClass(int x, int y){
		return controller.findNearestClass(x, y);
	}

}
