package main.java.app.swing.frame;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.java.app.state.StatefulApplication;
import main.java.app.util.KeypadListener;

public abstract class StatefulPanel extends JPanel implements KeypadListener {

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

		this.addKeyListener(this);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
	
	public void processData() {};
    public void handleDisplay() {};
	
}
