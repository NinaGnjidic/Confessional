package main.java.app.swing.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.java.app.EnvironmentVariables;
import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.Button;
import main.java.app.swing.button.HashButton;
import main.java.app.swing.listener.ButtonListener;

public abstract class StatefulPanel extends JPanel implements ButtonListener {

	private static final long serialVersionUID = -6129290005971012152L;

	protected final StatefulApplication app;
	protected Image backgroundImage;
	private String title;
	private String text;

	protected Button label;
	protected Button rightButton;
	protected Button leftButton;

	protected StatefulPanel(StatefulApplication app, String backgroundImagePath, String title, String text) {
		this(app, new ImageIcon(StatefulPanel.class.getResource(backgroundImagePath)).getImage(), title, text);
	}

	protected StatefulPanel(StatefulApplication app, Image backgroundImage, String title, String text) {
		this.app = app;
		this.backgroundImage = backgroundImage;
		this.title = title;
		this.text = text;

		this.addKeyListener(this);
	}

	public void processData() {}

	public void handleDisplay() {
		this.setLayout(new BorderLayout());

		if (title != null) {
			label = new Button(title, null, app.getFont().deriveFont(Font.BOLD, 24));
			this.add(label, BorderLayout.NORTH);
		}

		Component centerArea = displayCenter(this.text);
		if (centerArea != null)
			this.add(centerArea, BorderLayout.CENTER);

		Component bottomArea = displayBottom();
		if(bottomArea != null)
			this.add(bottomArea, BorderLayout.SOUTH);
		
//		this.revalidate();
//		this.repaint();
		
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	protected Component displayCenter(String text) {
		if (text != null) {
			JTextArea textArea = new JTextArea(text);
			textArea.setForeground(Color.WHITE);
			textArea.setFont(app.getFont());
			textArea.setWrapStyleWord(true);
			textArea.setLineWrap(true);
			textArea.setEditable(false);
			textArea.setOpaque(false);
			textArea.setBorder(BorderFactory.createEmptyBorder(100, 170, 0, 160));
			return textArea;
		}
		return null;
	}
	
	protected Component displayBottom() {
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);
		rightButton = new HashButton(EnvironmentVariables.PREVIOUS_BUTTON_LABEL, app.getFont());
		bottomPanel.add(rightButton, BorderLayout.EAST);
		return bottomPanel;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}
	
}
