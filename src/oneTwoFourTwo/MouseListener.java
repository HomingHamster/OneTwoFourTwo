package oneTwoFourTwo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseListener implements MouseMotionListener{
	private DiagramPanel diagramPanel;
	
	public MouseListener (DiagramPanel diagramPanel){
		this.diagramPanel = diagramPanel;
	}
//	
	public void mouseMoved(MouseEvent mouseEvent) {
//		this.diagramPanel.setCoordinates(mouseEvent.getX(),mouseEvent.getY());
	}
//	
	public void mouseDragged(MouseEvent e) {
//		Rectangle thisOne=colourPane.FindNearestClass(e.getX(),e.getY());
//		
//		if (thisOne!=null)
//		{
//			int varx = e.getX() - thisOne.getX();
//			int vary = e.getY() - thisOne.getY();
//	     thisOne.setRect(e.getX()+varx,e.getY()+vary);
//		}
//		colourPane.repaint();
	}
}
