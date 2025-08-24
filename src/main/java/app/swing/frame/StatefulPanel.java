package main.java.app.swing.frame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.java.app.state.State;
import main.java.app.state.StatefulApplication;
import main.java.app.util.KeypadListener;

public abstract class StatefulPanel extends JPanel implements State, KeypadListener  {

	private static final long serialVersionUID = -6129290005971012152L;
	private static final String DEFAULT_BACKGROUND_IMAGE_PATH = "/images/background.jpg";

	protected final StatefulApplication app;
	protected Image backgroundImage;
	
	protected StatefulPanel(StatefulApplication app) {
		this(app, DEFAULT_BACKGROUND_IMAGE_PATH);
	}
	
	protected StatefulPanel(StatefulApplication app, String backgroundImagePath) {
		this(app, new ImageIcon(StatefulPanel.class.getResource(backgroundImagePath)).getImage());
	}

	protected StatefulPanel(StatefulApplication app, Image backgroundImage) {
		this.app = app;
		this.backgroundImage = backgroundImage;
		
		init();
	}
	
    private void init() {
		this.addKeyListener(this);
    	
    	this.addComponentListener(new ComponentAdapter() {
	        @Override
	        public void componentResized(ComponentEvent e) {
	            onComponentResized();
	        }
	    });
	}
    
    //TODO: not fully implemented yet
    protected void onComponentResized() {
		// it's okay to do nothing
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
	
}
