package oneTwoFourTwo;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

//The following because we are not going to be deserialzing 
//in another dimension any time soon.
@SuppressWarnings("serial")

public class DiagramPanel extends JPanel{
	private MouseListener mouseListener = new MouseListener(this);
	
	public DiagramPanel() {
		this.setBackground(Color.GRAY);
		this.addMouseMotionListener(mouseListener);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
