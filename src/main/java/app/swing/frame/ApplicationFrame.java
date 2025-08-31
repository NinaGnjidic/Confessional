package main.java.app.swing.frame;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ApplicationFrame extends JFrame {

	private static final long serialVersionUID = -7617117210435008140L;

	private String title;
	
	private JPanel cardPanel;
    private CardLayout cardLayout;

	public ApplicationFrame(String title) {
		this.title = title;
	}
	
	public void init(StatefulPanel firstPanel) {
		this.setTitle(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		
		cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        this.add(cardPanel);
        this.setVisible(true);
        
        show(firstPanel);
	}
	
	public void show(StatefulPanel newPanel) {
		newPanel.processData();
		newPanel.handleDisplay();

		cardPanel.removeAll();
	    cardPanel.add(newPanel);
	    cardPanel.revalidate();
	    cardPanel.repaint();

	    newPanel.setVisible(true);
	    newPanel.setFocusable(true);
		SwingUtilities.invokeLater(newPanel::requestFocusInWindow);
	}

}
