/**
 * Based on classes Written by
 * @author Amy James (arj18)
 * Modified by Felix Farquharson (fef)
 * This class contains a pannel that contains the buttons and also
 * the diagram pannel.
 */


package oneTwoFourTwo;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

//The following because we are not going to be deserialzing 
//in another dimension any time soon.
@SuppressWarnings("serial")

public class MainPanel extends JPanel {
	JButton newClass, addLink;
    GridBagLayout gridBag;
    GridBagConstraints cons;
	
	public MainPanel(DiagramPanel diagramPanel){
		MainPanelButtonListener buttonListener =
				new MainPanelButtonListener(diagramPanel);
		
		gridBag = new GridBagLayout();
		cons = new GridBagConstraints();
		this.setLayout(gridBag);
		gridBag.layoutContainer(this);
		
		/*
		 * Make a button, add to grid (0,0) and watch for it being clicked.
		 */
		JButton newClassButton = new JButton("New Class");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHWEST;
        cons.gridx = 0;
        cons.gridy = 0;
        cons.weightx = 1.0;
        gridBag.setConstraints(newClassButton,cons);
		this.add(newClassButton);
		newClassButton.addActionListener(buttonListener);
		
		/*
		 * Make a button, add to grid (0,1) and watch for it being clicked.
		 */
		JButton addLinkButton = new JButton("Add Link"); 
		cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 1;
        cons.weightx = 1.0;
        gridBag.setConstraints(addLinkButton,cons);
		this.add(addLinkButton);
		addLinkButton.addActionListener(buttonListener);
		
		/*
		 * Make a button, add to grid (0,2) and watch for it being clicked.
		 */
		JButton generateButton = new JButton("Generate Java Code"); 
		cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 3;
        cons.weightx = 1.0;
        gridBag.setConstraints(generateButton,cons);
		this.add(generateButton);
		generateButton.addActionListener(buttonListener);
		
		/*
		 * Make a button, add to grid (0,2) and watch for it being clicked.
		 */
		JButton editLinkButton = new JButton("Edit or Delete a Link"); 
		cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 2;
        cons.weightx = 1.0;
        gridBag.setConstraints(editLinkButton,cons);
		this.add(editLinkButton);
		editLinkButton.addActionListener(buttonListener);
	
		/*
		 * Make a button, add to grid (0,2) and watch for it being clicked.
		 */
		JButton helpButton = new JButton("Get Help"); 
		cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 4;
        cons.weightx = 1.0;
        gridBag.setConstraints(helpButton,cons);
		this.add(helpButton);
		helpButton.addActionListener(buttonListener);
		
		/*
		 * Stick the diagram pannel on the right.
		 */
		cons.fill = GridBagConstraints.BOTH;
        cons.gridx = 1;
        cons.gridy = 0;
        cons.weightx = 6.0;
        cons.weighty = 1.0;
        cons.gridheight = 99;
        gridBag.setConstraints(diagramPanel,cons);
		this.add(diagramPanel);
	}
}
