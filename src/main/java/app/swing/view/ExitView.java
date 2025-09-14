package main.java.app.swing.view;

import static main.java.app.EnvironmentVariables.END_TEXT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.swing.frame.StatefulPanelWithButtons;

public class ExitView extends StatefulPanel {

	private static final long serialVersionUID = -6705770994443136416L;

	private static final String BACKGROUND_IMAGE_PATH = "/images/bg_default.jpg";

	private static final Image BACKGROUND_IMAGE = new ImageIcon(
			StatefulPanelWithButtons.class.getResource(BACKGROUND_IMAGE_PATH)).getImage();

	public ExitView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE);
	}

	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(300, 150, 100, 150));
		JTextArea textArea = new JTextArea(END_TEXT);
		textArea.setForeground(Color.YELLOW);
		textArea.setFont(app.getFont().deriveFont(Font.BOLD, 24));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		this.add(textArea, BorderLayout.CENTER);
	}

	@Override
	public void bigRedButtonPressed() {
		this.app.clearSelected();
		app.stopSound();
		app.show(new InsertCoinView(app));
	}

}
