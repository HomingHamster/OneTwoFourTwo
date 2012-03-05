//TEST
package oneTwoFourTwo;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

//The following because we are not going to be deserialzing 
//in another dimension any time soon.
@SuppressWarnings("serial")

public class MainPanel extends JPanel {
	JButton newClass, addLink;
	
	public MainPanel(DiagramPanel diagramPanel){
		MainPanelButtonListener buttonListener =
				new MainPanelButtonListener(diagramPanel);
		
		this.setLayout(new BorderLayout());
		
		JButton newClassButton = new JButton("New Class");
		this.add(newClassButton, BorderLayout.LINE_START);
		newClassButton.addActionListener(buttonListener); // Check out buttlist.com
		
		JButton addLinkButton = new JButton("Add Link"); 
		this.add(addLinkButton, BorderLayout.LINE_END);
	
		this.add(diagramPanel, BorderLayout.CENTER);
	}
}
